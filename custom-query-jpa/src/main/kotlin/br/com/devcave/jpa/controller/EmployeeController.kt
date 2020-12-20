package br.com.devcave.jpa.controller

import br.com.devcave.jpa.domain.Employee
import br.com.devcave.jpa.domain.EmployeeRequest
import br.com.devcave.jpa.service.EmployeeService
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("employees")
class EmployeeController(
    private val employeeService: EmployeeService
) {

    @PostMapping
    fun create(@RequestBody request: EmployeeRequest): HttpEntity<Any?> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(employeeService.createEmployee(request))
    }

    @GetMapping
    fun get(): HttpEntity<List<Employee>> {
        return ResponseEntity
            .ok(employeeService.findAll())
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): HttpEntity<Any?> {
        employeeService.deleteEmployee(id)
        return ResponseEntity
            .noContent().build()
    }

    @GetMapping("more-than")
    fun getEmployeesWithInvoicesGreaterThan250(): HttpEntity<List<Employee>> {
        return ResponseEntity
            .ok(employeeService.getEmployeesWithInvoicesGreaterThan250())
    }

    @GetMapping("count")
    fun countInvoices(): HttpEntity<List<Pair<*, *>>> {
        return ResponseEntity
            .ok(employeeService.countInvoices())
    }
}