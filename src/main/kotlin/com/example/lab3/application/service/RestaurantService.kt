package com.example.lab3.application.service

import com.example.lab3.application.exception.AlreadyExistsException
import com.example.lab3.application.exception.NotFoundByIdException
import com.example.lab3.domain.model.Dish
import com.example.lab3.domain.model.Restaurant
import com.example.lab3.domain.port.RestaurantRepositoryPort
import org.springframework.stereotype.Service

@Service
open class RestaurantService(
    private val restaurantRepository: RestaurantRepositoryPort
) {
    fun create(restaurant: Restaurant): Restaurant {
        val existing = restaurantRepository.findByName(restaurant.name)
        if (existing != null) throw AlreadyExistsException("Restaurant", "name", restaurant.name)
        return restaurantRepository.create(restaurant)
    }

    fun getById(id: Long): Restaurant =
        restaurantRepository.findById(id) ?: throw NotFoundByIdException("Restaurant", id)

    fun getAll(): List<Restaurant> = restaurantRepository.findAll()

    fun update(id: Long, updated: Restaurant): Restaurant {
        restaurantRepository.findById(id) ?: throw NotFoundByIdException("Restaurant", id)
        val withSameName = restaurantRepository.findByName(updated.name)
        if (withSameName != null && withSameName.id != id)
            throw AlreadyExistsException("Restaurant", "name", updated.name)
        return restaurantRepository.update(updated.copy(id = id))
    }

    fun delete(id: Long) {
        restaurantRepository.findById(id) ?: throw NotFoundByIdException("Restaurant", id)
        restaurantRepository.delete(id)
    }

    fun getDishes(restaurantId: Long): List<Dish> {
        restaurantRepository.findById(restaurantId) ?: throw NotFoundByIdException("Restaurant", restaurantId)
        return restaurantRepository.findDishesByRestaurantId(restaurantId)
    }
}