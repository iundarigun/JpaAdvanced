package br.com.devcave.jpa.controller

import br.com.devcave.jpa.domain.Invoice
import br.com.devcave.jpa.domain.InvoiceRequest
import br.com.devcave.jpa.service.InvoiceService
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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

    @PostMapping
    fun create(@RequestBody request: InvoiceRequest): HttpEntity<Any?> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(invoiceService.createInvoice(request))
    }
}