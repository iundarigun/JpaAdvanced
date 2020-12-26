package br.com.devcave.jpa.service

import br.com.devcave.jpa.domain.Employee
import br.com.devcave.jpa.domain.Invoice
import br.com.devcave.jpa.domain.InvoiceRequest
import br.com.devcave.jpa.repository.InvoiceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InvoiceService(
    private val employeeService: EmployeeService,
    private val invoiceRepository: InvoiceRepository
) {
    @Transactional
    fun findAll(): List<Invoice> {
        return invoiceRepository.findAll()
    }

    fun createInvoice(request: InvoiceRequest): Long {
        return invoiceRepository.save(
            request.toEntity(employeeService.getById(request.employeeId))
        ).id
    }

    private fun InvoiceRequest.toEntity(employee: Employee): Invoice =
        Invoice(
            reference = this.reference,
            value = this.value,
            employee = employee
        )
}
