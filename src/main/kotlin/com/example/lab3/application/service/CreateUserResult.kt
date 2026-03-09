package com.example.lab3.application.service

import com.example.lab3.domain.model.User

data class CreateUserResult(
    val user: User,
    val isCreated: Boolean
)