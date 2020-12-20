package br.com.devcave.jpa.domain

data class InvoiceRequest(
    var reference: String,
    var value: Long,
    var employeeId: Long
)