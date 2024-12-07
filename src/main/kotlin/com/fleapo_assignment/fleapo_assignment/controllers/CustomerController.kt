package com.fleapo_assignment.fleapo_assignment.controllers

import com.fleapo_assignment.fleapo_assignment.model.Course
import com.fleapo_assignment.fleapo_assignment.model.User
import com.fleapo_assignment.fleapo_assignment.service.CourseService
import com.fleapo_assignment.fleapo_assignment.service.PurchaseService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(private val courseService: CourseService, private val purchaseService: PurchaseService) {

    @GetMapping("/course")
    fun getCourses(
        @RequestParam(required = false) search: String?
    ): ResponseEntity<List<Course>> {
        val courses = courseService.getCourses(search)
        return if (courses.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(courses)
        }
    }

    @GetMapping("/buy/course/{id}")
    fun buyCourse(
        @PathVariable id: Long,
        @AuthenticationPrincipal user: User // The currently authenticated user (Customer)
    ): ResponseEntity<String> {
        val userId = user.id!!.toLong()
        try {
            purchaseService.purchaseCourse(id, userId)
            return ResponseEntity.ok("Course purchased successfully!")
        } catch (ex: Exception) {
            return ResponseEntity.status(400).body("Error: ${ex.message}")
        }
    }
}
