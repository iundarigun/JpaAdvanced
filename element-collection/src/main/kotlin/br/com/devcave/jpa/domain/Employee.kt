package br.com.devcave.jpa.domain

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Version

@Entity
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var name: String,

    var document: String,

    var bornAt: LocalDate,

    val active: Boolean = true,

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "role") // If the column on employee_role is the same of the attribute in the entity, this annotation is not necessary
    @CollectionTable(name = "employee_role", joinColumns = [JoinColumn(name = "employee_id")]) // If the table is <entity>_<attribute> and the id is <entity>_id, this annotation is no necessary
    val roles: MutableSet<Role> = mutableSetOf(),

    @Version
    val version: Long = 1L,

    @CreationTimestamp
    var createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null
) 