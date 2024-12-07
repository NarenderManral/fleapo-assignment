package com.fleapo_assignment.fleapo_assignment.repository

import com.fleapo_assignment.fleapo_assignment.model.Course
import com.fleapo_assignment.fleapo_assignment.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Course, Long> {
    fun findByCreator(creator: User): List<Course>
    fun findByTitleContainingOrDescriptionContaining(titleToSearch: String, descriptionToSearch: String): List<Course>
}