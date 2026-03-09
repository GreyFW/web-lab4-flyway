package com.example.lab3.adapter.web.dto

data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String
)