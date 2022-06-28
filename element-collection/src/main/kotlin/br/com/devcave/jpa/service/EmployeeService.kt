package br.com.devcave.jpa.service

import br.com.devcave.jpa.domain.Employee
import br.com.devcave.jpa.domain.EmployeeRequest
import br.com.devcave.jpa.repository.EmployeeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmployeeService(
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
        employeeRepository.findById(id).ifPresent {
            it.roles.clear()
            it.roles.addAll(request.roles)
            employeeRepository.save(it)
        }

    }

    private fun EmployeeRequest.toEntity(): Employee =
        Employee(
            name = this.name,
            document = this.document,
            bornAt = this.bornAt,
            roles = this.roles
        )

}
