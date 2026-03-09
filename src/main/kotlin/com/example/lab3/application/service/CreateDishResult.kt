package com.example.lab3.application.service

import com.example.lab3.domain.model.Dish

data class CreateDishResult(
    val dish: Dish,
    val isCreated: Boolean
)