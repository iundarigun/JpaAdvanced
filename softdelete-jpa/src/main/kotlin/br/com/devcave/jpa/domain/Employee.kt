package br.com.devcave.jpa.domain

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.annotations.Where
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Version

@Entity
@SQLDelete(sql = """UPDATE employee 
    SET active=false, updated_at = now(), version = version + 1 
    WHERE id= ? and version = ?""")
@Where(clause = "active = true")
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var name: String,

    var document: String,

    var bornAt: LocalDate,

    val active: Boolean = true,

    @Version
    val version: Long = 0L,

    @CreationTimestamp
    var createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null
)