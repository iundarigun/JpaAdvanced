package br.com.devcave.jpa.service

import br.com.devcave.jpa.domain.Employee
import br.com.devcave.jpa.domain.EmployeeInvoiceResponse
import br.com.devcave.jpa.domain.EmployeeRequest
import br.com.devcave.jpa.repository.EmployeeRepository
import br.com.devcave.jpa.repository.GenericSearchSpecification
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

    @Transactional
    fun deleteEmployee(id: Long) {
        employeeRepository.deleteById(id)
    }

    @Transactional
    fun findAll(): List<Employee> {
        return employeeRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): Employee =
        employeeRepository.findById(id).orElseThrow()

    private fun EmployeeRequest.toEntity(): Employee =
        Employee(
            name = this.name,
            document = this.document,
            bornAt = this.bornAt
        )

    fun searchEmployeesWithText(text: String): List<Employee> {
        return employeeRepository.findAll(GenericSearchSpecification(listOf("name", "document"), text))
    }
}
