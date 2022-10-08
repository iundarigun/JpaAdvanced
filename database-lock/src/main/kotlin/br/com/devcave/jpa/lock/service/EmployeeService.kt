package br.com.devcave.jpa.lock.service

import br.com.devcave.jpa.lock.configuration.PostgresLockManager
import br.com.devcave.jpa.lock.domain.Employee
import br.com.devcave.jpa.lock.domain.EmployeeRequest
import br.com.devcave.jpa.lock.repository.EmployeeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration

@Service
class EmployeeService(
        private val postgresLockManager: PostgresLockManager,
        private val employeeRepository: EmployeeRepository
) {
    @Transactional
    fun createEmployee(employeeRequest: EmployeeRequest): Long {
        if (employeeRepository.existsByDocument(employeeRequest.document)) {
            throw RuntimeException("Employee already exists")
        }
        return employeeRepository.save(employeeRequest.toEntity()).id
    }

    @Transactional(readOnly = true)
    fun findAll(): List<Employee> {
        return employeeRepository.findAll()
    }

    @Transactional
    fun update(id: Long, request: EmployeeRequest) {
        postgresLockManager.tryWithLock(id, Duration.ofMillis(500)) {
            Thread.sleep(1000) // To simulate simultaneous execution
            employeeRepository.findById(id).ifPresent {
                it.name = request.name
                it.document = request.document
                it.bornAt = request.bornAt
                employeeRepository.save(it)
            }
        }
    }

    private fun EmployeeRequest.toEntity(): Employee =
            Employee(
                    name = this.name,
                    document = this.document,
                    bornAt = this.bornAt
            )

}
