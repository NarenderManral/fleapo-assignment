package com.fleapo_assignment.fleapo_assignment.repository

import com.fleapo_assignment.fleapo_assignment.model.Purchase
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface PurchaseRepository : JpaRepository<Purchase, Long> {
    fun findByPurchaseDateBetween(startDate: LocalDateTime, endDate: LocalDateTime): List<Purchase>
}
