package com.example.lab3.application.service

import com.example.lab3.application.exception.AlreadyExistsException
import com.example.lab3.application.exception.NotFoundByIdException
import com.example.lab3.domain.model.Dish
import com.example.lab3.domain.port.DishRepositoryPort
import com.example.lab3.domain.port.RestaurantRepositoryPort
import org.springframework.stereotype.Service

@Service
open class DishService(
    private val dishRepository: DishRepositoryPort,
    private val restaurantRepository: RestaurantRepositoryPort
) {
    fun create(dish: Dish): CreateDishResult {
        restaurantRepository.findById(dish.restaurantId)
            ?: throw NotFoundByIdException("Restaurant", dish.restaurantId)

        val existing = dishRepository.findByName(dish.name)
        return if (existing != null) {
            CreateDishResult(existing, false)
        } else {
            val created = dishRepository.create(dish)
            CreateDishResult(created, true)
        }
    }

    fun getById(id: Long): Dish =
        dishRepository.findById(id) ?: throw NotFoundByIdException("Dish", id)

    fun update(id: Long, updatedDish: Dish): Dish {
        val existingDish = dishRepository.findById(id)
            ?: throw NotFoundByIdException("Dish", id)

        val dishWithSameName = dishRepository.findByName(updatedDish.name)
        if (dishWithSameName != null && dishWithSameName.id != id)
            throw AlreadyExistsException("Dish", "name", updatedDish.name)

        val dishToSave = existingDish.copy(
            name = updatedDish.name,
            description = updatedDish.description,
            price = updatedDish.price,
            isAvailable = updatedDish.isAvailable
        )
        return dishRepository.update(dishToSave)
    }

    fun delete(id: Long) {
        dishRepository.findById(id) ?: throw NotFoundByIdException("Dish", id)
        dishRepository.delete(id)
    }

    fun getAll(namePart: String? = null): List<Dish> =
        dishRepository.findAll(namePart)
}