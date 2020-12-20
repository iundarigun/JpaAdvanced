package br.com.devcave.jpa.repository

import br.com.devcave.jpa.domain.Employee
import br.com.devcave.jpa.domain.Invoice
import javax.persistence.EntityManager
import javax.persistence.Tuple
import javax.persistence.criteria.CriteriaBuilder


class EmployeeRepositoryCustomImpl(
    private val entityManager: EntityManager
) : EmployeeRepositoryCustom {

    override fun getEmployeesWithInvoicesGreaterThan250(): List<Employee> {
        val criteriaBuilder = entityManager.criteriaBuilder
        val parameter = criteriaBuilder.parameter(Long::class.java)
        val cq = criteriaBuilder
            .createQuery(Employee::class.java).also {
                val join = it.from(Employee::class.java).join<Employee, Invoice>("invoices")
                it.where(criteriaBuilder.gt(join.get("value"), parameter))
                it.distinct(true)
            }

        return entityManager.createQuery(cq).also {
            it.setParameter(parameter, 250L)
        }.resultList
    }

    override fun countInvoices(): List<Pair<*, *>> {
        val criteriaBuilder = entityManager.criteriaBuilder
        val cq = criteriaBuilder
            .createQuery(Pair::class.java).also {
                val from = it.from(Employee::class.java)
                val join = from.join<Employee, Invoice>("invoices")
                it.multiselect(from.get<String>("name"), criteriaBuilder.count(join.get<Long>("id")))
                it.groupBy(from.get<Long>("id"), from.get<String>("name"))
            }
        return entityManager.createQuery(cq).resultList
    }
}