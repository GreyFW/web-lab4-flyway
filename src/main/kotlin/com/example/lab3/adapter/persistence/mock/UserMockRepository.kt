package com.example.lab3.adapter.persistence.mock

import com.example.lab3.application.exception.*
import com.example.lab3.domain.model.User
import com.example.lab3.domain.port.UserRepositoryPort
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository

@Repository
@Profile("mock")
open class UserMockRepository : UserRepositoryPort {
    private val usersStorage = mutableMapOf<Long,User>()
    private var idCounter = 1L

    override fun create(user: User): User {
        val existingUser = usersStorage.values.find { it.email == user.email }
        if (existingUser != null ) {
            throw AlreadyExistsException("User", "email", user.email)
        }
        val id = idCounter++
        val newUser = user.copy(id = id)
        usersStorage[id] = newUser

        return newUser
    }

    override fun findById(id: Long): User? {
        return usersStorage[id]
    }

    override fun findAll(): List<User> {
        return usersStorage.values.toList()
    }

    override fun findByEmail(email: String): User? {
        return usersStorage.values.find { it.email == email }
    }

    override fun update(user: User): User {
        val id = user.id ?: throw IdCantBeNullException("User")
        val existingUser = usersStorage[id] ?: throw NotFoundByIdException("User", id)

        val updatedUser = existingUser.copy(
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            isActive = user.isActive
        )
        usersStorage[id] = updatedUser

        return updatedUser
    }

    override fun delete(id: Long) {
        val user = usersStorage[id] ?: throw NotFoundByIdException("User", id)
        usersStorage.remove(id)
    }
}