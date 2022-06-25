package br.com.devcave.jpa.repository

import br.com.devcave.jpa.domain.Document
import br.com.devcave.jpa.domain.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Long> {
    fun existsByDocument(document: Document): Boolean
}