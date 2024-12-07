package com.fleapo_assignment.fleapo_assignment.model

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "purchases")
data class Purchase(
    @Id @GeneratedValue
    val id: Long = 0,

    @ManyToOne
    val user: User, // The customer who bought the course

    @ManyToOne
    val course: Course, // The course that was purchased

    val purchaseDate: LocalDateTime = LocalDateTime.now()
)
