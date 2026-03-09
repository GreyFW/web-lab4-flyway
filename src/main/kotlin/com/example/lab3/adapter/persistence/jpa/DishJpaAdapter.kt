package com.example.lab3.adapter.persistence.jpa

import com.example.lab3.adapter.persistence.jpa.entity.DishEntity
import com.example.lab3.adapter.persistence.jpa.repository.DishJpaRepository
import com.example.lab3.adapter.persistence.jpa.repository.RestaurantJpaRepository
import com.example.lab3.domain.model.Dish
import com.example.lab3.domain.port.DishRepositoryPort
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository

@Repository
@Profile("jpa")
open class DishJpaAdapter(
    private val dishJpaRepository: DishJpaRepository,
    private val restaurantJpaRepository: RestaurantJpaRepository
) : DishRepositoryPort {

    override fun create(dish: Dish): Dish {
        val restaurantEntity = restaurantJpaRepository.findById(dish.restaurantId).orElseThrow()
        return dishJpaRepository.save(DishEntity.fromDomain(dish, restaurantEntity)).toDomain()
    }

    override fun findById(id: Long): Dish? =
        dishJpaRepository.findById(id).orElse(null)?.toDomain()

    override fun findAll(namePart: String?): List<Dish> =
        if (namePart.isNullOrBlank()) dishJpaRepository.findAll().map { it.toDomain() }
        else dishJpaRepository.findAllByNamePart(namePart).map { it.toDomain() }

    override fun findByName(name: String): Dish? =
        dishJpaRepository.findByName(name)?.toDomain()

    override fun update(dish: Dish): Dish {
        val restaurantEntity = restaurantJpaRepository.findById(dish.restaurantId).orElseThrow()
        return dishJpaRepository.save(DishEntity.fromDomain(dish, restaurantEntity)).toDomain()
    }

    override fun delete(id: Long) =
        dishJpaRepository.deleteById(id)
}