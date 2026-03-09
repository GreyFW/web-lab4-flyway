package com.example.lab3.adapter.web.dto.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserCreateRequest(
    @field:Email
    @field:NotBlank
    val email: String,

    @field:NotBlank val firstName: String,

    @field:NotBlank val lastName: String,

    val isActive: Boolean = true
)