package com.fleapo_assignment.fleapo_assignment.controllers

import com.fleapo_assignment.fleapo_assignment.model.LoginRequest
import com.fleapo_assignment.fleapo_assignment.model.UserDto
import com.fleapo_assignment.fleapo_assignment.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@RestController
class UserController(private val userService: UserService) {

    private val passwordEncoder = BCryptPasswordEncoder()

    @PostMapping("/signup")
    fun signup(@Valid @RequestBody userDto: UserDto): ResponseEntity<Any> {
        return try {
            val user = userService.createUser(userDto)
            ResponseEntity.ok(user)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Error during signup: ${e.message}")
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Map<String, String>> {
        return try {
            val token = userService.authenticateUser(loginRequest)
            ResponseEntity.ok(mapOf("token" to token))
        } catch (e: Exception) {
            ResponseEntity.status(401).body(mapOf("message" to "Invalid credentials"))
        }
    }
}
