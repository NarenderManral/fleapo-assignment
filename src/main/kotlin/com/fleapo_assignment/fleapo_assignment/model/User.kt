package com.fleapo_assignment.fleapo_assignment.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val email: String,
    val password: String,
    @Enumerated(EnumType.STRING) val role: Role
) {
    constructor() : this(null, "", "", Role.Customer)
}

enum class Role {
    Admin, Creator, Customer
}