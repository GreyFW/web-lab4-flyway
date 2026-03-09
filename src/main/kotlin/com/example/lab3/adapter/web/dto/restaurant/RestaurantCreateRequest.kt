package com.example.lab3.adapter.web.dto.restaurant

import jakarta.validation.constraints.NotBlank

data class RestaurantCreateRequest(
    @field:NotBlank val name: String,
    @field:NotBlank val address: String
)