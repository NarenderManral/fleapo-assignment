package com.fleapo_assignment.fleapo_assignment.model

import jakarta.persistence.*

@Entity
@Table(name = "courses")
data class Course(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val title: String,
    val description: String,
    val price: Double,
    @ManyToOne val creator: User
)
