package br.com.devcave.jpa.service

import br.com.devcave.jpa.domain.Invoice
import br.com.devcave.jpa.repository.InvoiceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InvoiceService(
    private val invoiceRepository: InvoiceRepository
) {
    @Transactional
    fun findAll(): List<Invoice> {
        return invoiceRepository.findAll()
    }

}
