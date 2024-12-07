package com.fleapo_assignment.fleapo_assignment.model

data class StatisticsResponse(
    val purchases: List<Purchase>,
    val totalAmountPaid: Double
)
