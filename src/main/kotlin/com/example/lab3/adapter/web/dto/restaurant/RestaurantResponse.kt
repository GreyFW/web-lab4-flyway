package com.example.lab3.adapter.web.dto.restaurant

import jakarta.validation.constraints.NotBlank

data class RestaurantResponse(
    val id: Long,
    val name: String,
    val address: String
)