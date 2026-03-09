package com.example.lab3.adapter.web.mapper

import com.example.lab3.adapter.web.dto.dish.*
import com.example.lab3.domain.model.Dish

object DishMapper {
    fun toDomain(request: DishCreateRequest): Dish =
        Dish(
            id = null,
            name = request.name,
            description = request.description,
            price = request.price,
            isAvailable = request.isAvailable
        )

    fun toDomain(id: Long, request: DishUpdateRequest): Dish =
        Dish(
            id = id,
            name = request.name,
            description = request.description,
            price = request.price,
            isAvailable = request.isAvailable
        )

    fun toResponse(dish: Dish): DishResponse =
        DishResponse(
            id = dish.id!!,
            name = dish.name,
            description = dish.description,
            price = dish.price,
            isAvailable = dish.isAvailable
        )
}
