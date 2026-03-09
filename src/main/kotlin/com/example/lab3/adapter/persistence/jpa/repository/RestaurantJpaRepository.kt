package com.example.lab3.adapter.persistence.jpa.repository

import com.example.lab3.adapter.persistence.jpa.entity.RestaurantEntity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface RestaurantJpaRepository : JpaRepository<RestaurantEntity, Long> {
    fun findByName(name: String): RestaurantEntity?

    @EntityGraph(attributePaths = ["dishes"])
    fun findWithDishesById(id: Long): RestaurantEntity?
}