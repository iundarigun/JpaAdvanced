package br.com.devcave.jpa.lock.domain

import java.time.LocalDate

data class EmployeeRequest(
    val name: String,
    val document: String,
    val bornAt: LocalDate = LocalDate.now()
)