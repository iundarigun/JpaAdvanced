package br.com.devcave.jpa.repository

import br.com.devcave.jpa.domain.Employee
import br.com.devcave.jpa.domain.EmployeeInvoiceResponse

interface EmployeeRepositoryCustom {
    fun getEmployeesWithInvoicesGreaterThan250(): List<Employee>

    fun countInvoices(): List<EmployeeInvoiceResponse>
}