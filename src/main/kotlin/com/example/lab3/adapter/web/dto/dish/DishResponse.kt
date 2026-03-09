package com.example.lab3.adapter.web.dto.dish

import java.math.BigDecimal

data class DishResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val isAvailable: Boolean,
    val restaurantId: Long
)