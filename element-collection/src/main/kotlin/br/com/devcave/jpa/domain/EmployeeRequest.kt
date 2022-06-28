package br.com.devcave.jpa.domain

import java.time.LocalDate

data class EmployeeRequest(
    val name: String,
    val document: String,
    val bornAt: LocalDate = LocalDate.now(),
    val roles: MutableSet<Role>
)