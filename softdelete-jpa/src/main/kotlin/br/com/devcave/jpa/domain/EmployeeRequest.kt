package br.com.devcave.jpa.domain

import java.time.LocalDate

data class EmployeeRequest(
    var name: String,
    var document: String,
    var bornAt: LocalDate = LocalDate.now()
)