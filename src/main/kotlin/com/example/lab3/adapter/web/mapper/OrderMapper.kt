package com.example.lab3.adapter.web.mapper

import com.example.lab3.adapter.web.dto.order.OrderDishResponse
import com.example.lab3.adapter.web.dto.order.OrderResponse
import com.example.lab3.domain.model.Order

object OrderMapper {
    fun toResponse(order: Order) = OrderResponse(
        id = order.id!!,
        userId = order.userId,
        status = order.status,
        createdAt = order.createdAt,
        dishes = order.dishes.map { dish ->
            OrderDishResponse(
                id = dish.id!!,
                name = dish.name,
                description = dish.description,
                price = dish.price,
                isAvailable = dish.isAvailable,
                restaurantId = dish.restaurantId
            )
        }
    )
}