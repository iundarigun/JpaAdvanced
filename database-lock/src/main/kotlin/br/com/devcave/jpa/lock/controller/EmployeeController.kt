package br.com.devcave.jpa.lock.controller

import br.com.devcave.jpa.lock.domain.Employee
import br.com.devcave.jpa.lock.domain.EmployeeRequest
import br.com.devcave.jpa.lock.service.EmployeeService
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody request: EmployeeRequest) {
        employeeService.update(id, request)
    }

    @GetMapping
    fun get(): HttpEntity<List<Employee>> {
        return ResponseEntity
            .ok(employeeService.findAll())
    }
}