package com.fleapo_assignment.fleapo_assignment.controllers

import com.fleapo_assignment.fleapo_assignment.model.Role
import com.fleapo_assignment.fleapo_assignment.model.User
import com.fleapo_assignment.fleapo_assignment.service.AdminService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.security.core.annotation.AuthenticationPrincipal

@RestController
class AdminController(private val adminService: AdminService) {

    @GetMapping("/user")
    fun getUsers(@AuthenticationPrincipal user: User): ResponseEntity<List<User>> {
        if (user.role == Role.Admin) {
            val users = adminService.getAllUsers()
            return ResponseEntity.ok(users)
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(emptyList())
    }
}
