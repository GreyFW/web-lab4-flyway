package com.example.lab3.adapter.persistence.jpa

import com.example.lab3.adapter.persistence.jpa.entity.OrderEntity
import com.example.lab3.adapter.persistence.jpa.repository.DishJpaRepository
import com.example.lab3.adapter.persistence.jpa.repository.OrderJpaRepository
import com.example.lab3.adapter.persistence.jpa.repository.UserJpaRepository
import com.example.lab3.domain.model.Order
import com.example.lab3.domain.model.OrderStatus
import com.example.lab3.domain.port.OrderRepositoryPort
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository

@Repository
@Profile("jpa")
open class OrderJpaAdapter(
    private val orderJpaRepository: OrderJpaRepository,
    private val userJpaRepository: UserJpaRepository,
    private val dishJpaRepository: DishJpaRepository
) : OrderRepositoryPort {

    override fun create(order: Order): Order {
        val userEntity = userJpaRepository.findById(order.userId).orElseThrow()
        val dishEntities = order.dishes.map { dish ->
            dishJpaRepository.findById(dish.id!!).orElseThrow()
        }.toMutableList()

        val entity = OrderEntity(
            user = userEntity,
            status = order.status,
            createdAt = order.createdAt,
            dishes = dishEntities
        )
        return orderJpaRepository.save(entity).toDomain()
    }

    override fun findById(id: Long): Order? =
        orderJpaRepository.findById(id).orElse(null)?.toDomain()

    override fun findAll(userId: Long?, status: OrderStatus?): List<Order> =
        orderJpaRepository.findAllFiltered(userId, status).map { it.toDomain() }

    override fun update(order: Order): Order {
        val existing = orderJpaRepository.findById(order.id!!).orElseThrow()
        val updated = OrderEntity(
            id = existing.id,
            user = existing.user,
            status = order.status,
            createdAt = existing.createdAt,
            dishes = existing.dishes
        )
        return orderJpaRepository.save(updated).toDomain()
    }
}