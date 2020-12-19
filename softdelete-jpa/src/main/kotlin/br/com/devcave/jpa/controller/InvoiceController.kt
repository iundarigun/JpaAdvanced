package br.com.devcave.jpa.controller

import br.com.devcave.jpa.domain.Invoice
import br.com.devcave.jpa.service.InvoiceService
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("invoices")
class InvoiceController(
    private val invoiceService: InvoiceService
) {

    @GetMapping
    fun get(): HttpEntity<List<Invoice>>{
        return ResponseEntity.ok(invoiceService.findAll())
    }
}