package com.example.lab3.adapter.persistence.jpa.repository

import com.example.lab3.adapter.persistence.jpa.entity.OrderEntity
import com.example.lab3.domain.model.OrderStatus
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OrderJpaRepository : JpaRepository<OrderEntity, Long> {

    @EntityGraph(value = "Order.withUserAndDishes")
    override fun findById(id: Long): java.util.Optional<OrderEntity>

    @Query("SELECT o FROM OrderEntity o " +
            "WHERE (:userId IS NULL OR o.user.id = :userId) " +
            "AND (:status IS NULL OR o.status = :status)")

    fun findAllFiltered(
        @Param("userId") userId: Long?,
        @Param("status") status: OrderStatus?
    ): List<OrderEntity>
}