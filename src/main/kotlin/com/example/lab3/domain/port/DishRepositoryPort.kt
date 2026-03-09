package com.example.lab3.domain.port

import com.example.lab3.domain.model.Dish

interface  DishRepositoryPort {
    // по CRUD
    fun create(dish: Dish): Dish                   // :C
    fun findById(id: Long): Dish?                  // :R - одно и все блюда
    fun findAll(namePart: String?): List<Dish>
    fun findByName(name: String): Dish?
    fun update(dish: Dish): Dish                   // :U
    fun delete(id: Long)                           // :D
}