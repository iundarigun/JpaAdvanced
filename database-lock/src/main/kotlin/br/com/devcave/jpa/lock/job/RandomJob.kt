package br.com.devcave.jpa.lock.job

import br.com.devcave.jpa.lock.configuration.PostgresLockManager
import br.com.devcave.jpa.lock.service.EmployeeService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class RandomJob(
        private val postgresLockManager: PostgresLockManager,
        private val employeeService: EmployeeService) {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val serialId = 1_000_000_001L

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    fun random() {
        logger.info("Executing job")
        postgresLockManager.tryWithLock(serialId) {
            logger.info("acquired!")
            employeeService.findAll().forEach {
                logger.info("$it")
            }
        }
    }
}