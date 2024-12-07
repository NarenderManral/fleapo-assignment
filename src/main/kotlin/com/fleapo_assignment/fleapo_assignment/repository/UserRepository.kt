package com.fleapo_assignment.fleapo_assignment.repository

import com.fleapo_assignment.fleapo_assignment.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}