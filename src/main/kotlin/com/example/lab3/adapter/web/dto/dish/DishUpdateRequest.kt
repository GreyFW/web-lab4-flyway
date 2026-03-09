package com.example.lab3.adapter.web.dto.dish

import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

data class DishUpdateRequest(
    @field:NotBlank val name: String,
    @field:NotBlank val description: String,
    val price: BigDecimal,
    val isAvailable: Boolean
)