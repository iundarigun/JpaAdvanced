package br.com.devcave.jpa.repository

import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Expression
import javax.persistence.criteria.Path
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class GenericSearchSpecification<T>(private val columns: List<String>, private val text: String) : Specification<T> {

    override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {
        val predicates = columns.map {
            val expression = buildExpression(it, root)
            criteriaBuilder.like(criteriaBuilder.upper(expression), "%${text.toUpperCase()}%")
        }
        return criteriaBuilder.or(*predicates.toTypedArray())
    }

    private fun buildExpression(column: String, path: Path<T>): Expression<String> {
        if (column.contains(".").not()) {
            return path.get(column)
        }
        return path.get(column.split("\\.").last())
    }
}