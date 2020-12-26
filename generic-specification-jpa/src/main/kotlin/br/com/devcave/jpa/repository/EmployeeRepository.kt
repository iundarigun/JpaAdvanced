package br.com.devcave.jpa.repository

import br.com.devcave.jpa.domain.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface EmployeeRepository : JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    fun existsByDocument(document: String): Boolean
}