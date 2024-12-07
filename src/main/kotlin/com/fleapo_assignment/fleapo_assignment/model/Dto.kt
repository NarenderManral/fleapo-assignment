package com.fleapo_assignment.fleapo_assignment.model

data class UserDto(
    val email: String,
    val password: String,
    val role: Role // Enum for Admin, Creator, or Customer
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class CourseDto(
    val title: String,
    val description: String,
    val price: Double,
    val creatorId: Long // The creator's ID from User entity
)
