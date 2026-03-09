package com.example.lab3.application.service

import com.example.lab3.application.exception.NotFoundByIdException
import com.example.lab3.application.exception.ValidationException
import com.example.lab3.domain.model.Order
import com.example.lab3.domain.model.OrderStatus
import com.example.lab3.domain.port.DishRepositoryPort
import com.example.lab3.domain.port.OrderRepositoryPort
import com.example.lab3.domain.port.UserRepositoryPort
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
open class OrderService(
    private val orderRepository: OrderRepositoryPort,
    private val userRepository: UserRepositoryPort,
    private val dishRepository: DishRepositoryPort
) {
    fun create(userId: Long, dishIds: List<Long>): Order {
        userRepository.findById(userId)
            ?: throw ValidationException("User with id=$userId not found")

        if (dishIds.isEmpty())
            throw ValidationException("Order must contain at least one dish")

        val dishes = dishIds.map { dishId ->
            dishRepository.findById(dishId)
                ?: throw ValidationException("Dish with id=$dishId not found")
        }

        val order = Order(
            id = null,
            userId = userId,
            status = OrderStatus.PENDING,
            createdAt = LocalDateTime.now(),
            dishes = dishes
        )
        return orderRepository.create(order)
    }

    fun getById(id: Long): Order =
        orderRepository.findById(id) ?: throw NotFoundByIdException("Order", id)

    fun getAll(userId: Long?, status: OrderStatus?): List<Order> =
        orderRepository.findAll(userId, status)

    fun updateStatus(id: Long, newStatus: OrderStatus): Order {
        val order = orderRepository.findById(id)
            ?: throw NotFoundByIdException("Order", id)

        val allowed = mapOf(
            OrderStatus.PENDING    to setOf(OrderStatus.CONFIRMED, OrderStatus.CANCELLED),
            OrderStatus.CONFIRMED  to setOf(OrderStatus.DELIVERED, OrderStatus.CANCELLED),
            OrderStatus.DELIVERED  to emptySet(),
            OrderStatus.CANCELLED  to emptySet()
        )
        if (newStatus !in (allowed[order.status] ?: emptySet<OrderStatus>())) {
            throw ValidationException(
                "Invalid status transition from ${order.status} to $newStatus"
            )
        }

        return orderRepository.update(order.copy(status = newStatus))
    }
}