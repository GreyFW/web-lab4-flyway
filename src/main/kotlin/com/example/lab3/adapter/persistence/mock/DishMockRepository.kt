package com.example.lab3.adapter.persistence.mock

import com.example.lab3.application.exception.*
import com.example.lab3.domain.model.Dish
import com.example.lab3.domain.port.DishRepositoryPort
import com.example.lab3.application.exception.NotFoundByIdException
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository

@Repository
@Profile("mock")
class DishMockRepository : DishRepositoryPort {
    private val dishesStorage = mutableMapOf<Long, Dish>()
    private var idCounter = 1L

    override fun create(dish: Dish): Dish {
        val existingDish = dishesStorage.values.find { it.name == dish.name }
        if (existingDish != null ) {
            throw AlreadyExistsException("Dish", "name", dish.name)
        }
        val id = idCounter++
        val newDish = dish.copy(id = id)
        dishesStorage[id] = newDish

        return newDish
    }

    override fun findById(id: Long): Dish? {
        return dishesStorage[id]
    }

    override fun findAll(namePart: String?): List<Dish> {
        if (namePart == null) {
            return dishesStorage.values.toList()
        }
        return dishesStorage.values
            .filter { it.name.contains(namePart, ignoreCase = true) }
            .toList()
    }

    override fun findByName(name: String): Dish? {
        return dishesStorage.values.find { it.name.equals(name, ignoreCase = true) }
    }

    override fun update(dish: Dish): Dish {
        val id = dish.id ?: throw IdCantBeNullException("Dish")
        val existingDish = dishesStorage[id] ?: throw NotFoundByIdException("Dish", id)

        val updatedDish = existingDish.copy(
            name = dish.name,
            description = dish.description,
            price = dish.price,
            isAvailable = dish.isAvailable
        )
        dishesStorage[id] = updatedDish

        return updatedDish
    }

    override fun delete(id: Long) {
        val dish = dishesStorage[id] ?: throw NotFoundByIdException("Dish", id)
        dishesStorage.remove(id)
    }
}