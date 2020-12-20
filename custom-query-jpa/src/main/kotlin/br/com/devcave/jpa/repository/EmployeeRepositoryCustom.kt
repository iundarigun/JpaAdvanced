package br.com.devcave.jpa.repository

import br.com.devcave.jpa.domain.Employee

interface EmployeeRepositoryCustom {
    fun getEmployeesWithInvoicesGreaterThan250(): List<Employee>

    fun countInvoices(): List<Pair<*, *>>
}