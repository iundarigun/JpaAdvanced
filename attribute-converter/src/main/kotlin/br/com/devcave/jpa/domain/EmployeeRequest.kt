package br.com.devcave.jpa.domain

import java.time.LocalDate

data class EmployeeRequest(
    var name: String,
    var document: Document,
    var bornAt: LocalDate = LocalDate.now()
)