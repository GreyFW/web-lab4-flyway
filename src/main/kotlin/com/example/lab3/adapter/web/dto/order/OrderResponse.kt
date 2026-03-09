package com.example.lab3.adapter.web.dto.order

import com.example.lab3.domain.model.OrderStatus
import java.time.LocalDateTime

data class OrderResponse(
    val id: Long,
    val userId: Long,
    val status: OrderStatus,
    val createdAt: LocalDateTime,
    val dishes: List<OrderDishResponse>
)