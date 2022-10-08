package br.com.devcave.jpa.lock.repository

import br.com.devcave.jpa.lock.domain.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Long> {
    fun existsByDocument(document: String): Boolean
}