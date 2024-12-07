package com.fleapo_assignment.fleapo_assignment.service

import com.fleapo_assignment.fleapo_assignment.exception.CourseNotFoundException
import com.fleapo_assignment.fleapo_assignment.exception.UserNotFoundException
import com.fleapo_assignment.fleapo_assignment.model.Purchase
import com.fleapo_assignment.fleapo_assignment.model.Role
import com.fleapo_assignment.fleapo_assignment.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class PurchaseService(
    private val courseRepository: CourseRepository,
    private val purchaseRepository: PurchaseRepository,
    private val userRepository: UserRepository // Assuming you have a UserRepository
) {

    @Transactional
    open fun purchaseCourse(courseId: Long, userId: Long): Purchase {
        // Check if the course exists
        val course = courseRepository.findById(courseId).orElseThrow {
            throw CourseNotFoundException("Course with id $courseId not found.")
        }

        // Check if the user exists and is a customer
        val user = userRepository.findById(userId).orElseThrow {
            throw UserNotFoundException("User with id $userId not found.")
        }

        if (user.role != Role.Customer) {
            throw IllegalAccessException("Only customers can buy courses.")
        }

        // Create and save the purchase
        val purchase = Purchase(user = user, course = course)
        return purchaseRepository.save(purchase)
    }
}
