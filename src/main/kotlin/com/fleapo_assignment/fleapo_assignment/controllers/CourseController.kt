package com.fleapo_assignment.fleapo_assignment.controllers

import com.fleapo_assignment.fleapo_assignment.model.CourseDto
import com.fleapo_assignment.fleapo_assignment.model.Course
import com.fleapo_assignment.fleapo_assignment.service.CourseService
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/creator")
class CourseController(private val courseService: CourseService) {

    @PostMapping("/course")
    fun createCourse(@RequestBody courseDto: CourseDto): ResponseEntity<Course> {
        val course = courseService.createCourse(courseDto)
        return ResponseEntity.status(201).body(course)
    }

    @GetMapping("/courses")
    fun getCourses(@RequestParam creatorId: Long): ResponseEntity<List<Course>> {
        val courses = courseService.getCoursesByCreator(creatorId)
        return ResponseEntity.ok(courses)
    }
}
