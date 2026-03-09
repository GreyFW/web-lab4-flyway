package com.example.lab3.adapter.web.dto.order

data class OrderDishResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: java.math.BigDecimal,
    val isAvailable: Boolean,
    val restaurantId: Long
)