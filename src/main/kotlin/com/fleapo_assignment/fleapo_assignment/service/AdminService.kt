package com.fleapo_assignment.fleapo_assignment.service

import com.fleapo_assignment.fleapo_assignment.model.User
import com.fleapo_assignment.fleapo_assignment.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AdminService(private val userRepository: UserRepository) {

    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }
}