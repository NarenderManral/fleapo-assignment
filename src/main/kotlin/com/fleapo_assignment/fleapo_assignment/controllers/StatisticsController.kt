package com.fleapo_assignment.fleapo_assignment.controllers

import com.fleapo_assignment.fleapo_assignment.model.StatisticsResponse
import com.fleapo_assignment.fleapo_assignment.service.StatisticsService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class StatisticsController(private val statisticsService: StatisticsService) {

    @GetMapping("/stats")
    fun getStatistics(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) startDate: LocalDateTime,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) endDate: LocalDateTime
    ): ResponseEntity<StatisticsResponse> {
        val statistics = statisticsService.getStatistics(startDate, endDate)
        if(statistics.purchases.isEmpty()) {
            return ResponseEntity.noContent().build()
        }
        return ResponseEntity.ok(statistics)
    }
}
