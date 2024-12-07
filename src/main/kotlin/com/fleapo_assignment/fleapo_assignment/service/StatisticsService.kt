package com.fleapo_assignment.fleapo_assignment.service

import com.fleapo_assignment.fleapo_assignment.model.StatisticsResponse
import com.fleapo_assignment.fleapo_assignment.repository.PurchaseRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class StatisticsService(private val purchaseRepository: PurchaseRepository) {

    fun getStatistics(startDate: LocalDateTime, endDate: LocalDateTime): StatisticsResponse {
        val purchases = purchaseRepository.findByPurchaseDateBetween(startDate, endDate)
        val totalAmountPaid = purchases.sumOf { it.course.price }
        return StatisticsResponse(purchases, totalAmountPaid)
    }
}
