package br.com.devcave.jpa.lock.configuration

import br.com.devcave.jpa.lock.exception.LockAcquiredException
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.retry.support.RetryTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception
import java.time.Duration

@Component
class PostgresLockManager(
        private val jdbcTemplate: JdbcTemplate
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun <T> tryWithLock(key: Long, timeout: Duration = Duration.ZERO, block: () -> T): T {
        logger.info("trying to acquire lock for $key")
        lock(key, timeout)
        return block.invoke()
    }

    private fun lock(key: Long, timeout: Duration) {
        if (timeout.toNanos() == 0L) {
            tryToLock(key)
        } else {
            var retry = RetryTemplate.builder()
                    .maxAttempts(3)
                    .exponentialBackoff(100, 2.0, timeout.toMillis(), true)
                    .retryOn(LockAcquiredException::class.java)
                    .traversingCauses()
                    .build()
            retry.execute<Unit, LockAcquiredException> {
                logger.info("trying key $key, count: ${it.retryCount}")
                tryToLock(key)
            }
        }

    }

    private fun tryToLock(key: Long) {
        val acquired = jdbcTemplate.queryForObject("select pg_try_advisory_xact_lock(?)",
                Boolean::class.java, key)
        if (acquired.not()) {
            logger.warn("Lock does not acquired for $key")
            throw LockAcquiredException()
        }
    }
}