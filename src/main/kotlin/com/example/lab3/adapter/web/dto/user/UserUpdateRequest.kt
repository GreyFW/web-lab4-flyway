package com.example.lab3.adapter.web.dto.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class UserUpdateRequest (
    @field:Email
    @field:NotBlank
    val email: String,

    @field:NotBlank
    val firstName: String,

    @field:NotBlank
    val lastName: String,

    @field:NotNull
    val isActive: Boolean
)
