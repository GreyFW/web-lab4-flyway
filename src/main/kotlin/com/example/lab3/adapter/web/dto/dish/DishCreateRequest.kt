package com.example.lab3.adapter.web.dto.dish

import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

data class DishCreateRequest (
    @field:NotBlank
    val name: String,

    @field:NotBlank
    val description: String,

    @field:NotNull
    val price: BigDecimal,

    @field:NotNull
    val isAvailable: Boolean
)