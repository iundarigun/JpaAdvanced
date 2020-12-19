package br.com.devcave.jpa.repository

import br.com.devcave.jpa.domain.Invoice
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface InvoiceRepository : JpaRepository<Invoice, Long> {
    @EntityGraph(attributePaths = ["employee"])
    override fun findAll(): List<Invoice>
}
