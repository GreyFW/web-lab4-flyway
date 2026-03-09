package com.example.lab3.domain.port

import com.example.lab3.domain.model.User

interface UserRepositoryPort {              // по CRUD
    fun create(user: User): User            // :C
    fun findById(id: Long): User?           // :R - один и все пользователи
    fun findAll(): List<User>
    fun findByEmail(email: String): User?
    fun update(user: User): User            // :U
    fun delete(id: Long)                    // :D
}