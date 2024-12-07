package com.fleapo_assignment.fleapo_assignment.service

import com.fleapo_assignment.fleapo_assignment.model.CourseDto
import com.fleapo_assignment.fleapo_assignment.model.Course
import com.fleapo_assignment.fleapo_assignment.repository.CourseRepository
import com.fleapo_assignment.fleapo_assignment.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class CourseService(private val courseRepository: CourseRepository, private val userRepository: UserRepository) {

    fun createCourse(courseDto: CourseDto): Course {
        val creator = userRepository.findById(courseDto.creatorId).orElseThrow { IllegalArgumentException("Creator not found") }
        val course = Course(title = courseDto.title, description = courseDto.description, price = courseDto.price, creator = creator)
        return courseRepository.save(course)
    }

    fun getCoursesByCreator(creatorId: Long): List<Course> {
        val creator = userRepository.findById(creatorId).orElseThrow { IllegalArgumentException("Creator not found") }
        return courseRepository.findByCreator(creator)
    }

    fun getCourses(search: String?): List<Course> {
        return if (!search.isNullOrEmpty()) {
            courseRepository.findByTitleContainingOrDescriptionContaining(search, search)
        } else {
            courseRepository.findAll()
        }
    }

}
