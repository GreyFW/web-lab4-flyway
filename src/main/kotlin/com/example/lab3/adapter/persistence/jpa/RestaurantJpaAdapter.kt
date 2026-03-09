package com.example.lab3.adapter.persistence.jpa

import com.example.lab3.adapter.persistence.jpa.entity.RestaurantEntity
import com.example.lab3.adapter.persistence.jpa.repository.RestaurantJpaRepository
import com.example.lab3.domain.model.Dish
import com.example.lab3.domain.model.Restaurant
import com.example.lab3.domain.port.RestaurantRepositoryPort
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository

@Repository
@Profile("jpa")
open class RestaurantJpaAdapter(
    private val restaurantJpaRepository: RestaurantJpaRepository
) : RestaurantRepositoryPort {

    override fun create(restaurant: Restaurant): Restaurant =
        restaurantJpaRepository.save(RestaurantEntity.fromDomain(restaurant)).toDomain()

    override fun findById(id: Long): Restaurant? =
        restaurantJpaRepository.findById(id).orElse(null)?.toDomain()

    override fun findAll(): List<Restaurant> =
        restaurantJpaRepository.findAll().map { it.toDomain() }

    override fun findByName(name: String): Restaurant? =
        restaurantJpaRepository.findByName(name)?.toDomain()

    override fun update(restaurant: Restaurant): Restaurant =
        restaurantJpaRepository.save(RestaurantEntity.fromDomain(restaurant)).toDomain()

    override fun delete(id: Long) =
        restaurantJpaRepository.deleteById(id)

    override fun findDishesByRestaurantId(restaurantId: Long): List<Dish> =
        restaurantJpaRepository.findWithDishesById(restaurantId)
            ?.dishes
            ?.map { it.toDomain() }
            ?: emptyList()
}