package com.example.lab3.adapter.web.dto.order

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class OrderCreateRequest(
    @field:NotNull val userId: Long,
    @field:NotEmpty val dishIds: List<Long>
)