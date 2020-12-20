package br.com.devcave.jpa.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Version

@Entity
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var name: String,

    var document: String,

    var bornAt: LocalDate,

    @field:JsonIgnore
    @field:OneToMany(mappedBy = "employee")
    var invoices: Set<Invoice> = emptySet(),

    val active: Boolean = true,

    @Version
    val version: Long = 0L,

    @CreationTimestamp
    var createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Employee

        if (id != other.id) return false
        if (name != other.name) return false
        if (document != other.document) return false
        if (bornAt != other.bornAt) return false
        if (active != other.active) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + document.hashCode()
        result = 31 * result + bornAt.hashCode()
        result = 31 * result + active.hashCode()
        return result
    }

    override fun toString(): String {
        return "Employee(id=$id, name='$name', document='$document', bornAt=$bornAt, active=$active, " +
                "version=$version, createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}