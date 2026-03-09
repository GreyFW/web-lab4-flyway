package com.example.lab3.application.service

import com.example.lab3.application.exception.AlreadyExistsException
import com.example.lab3.domain.model.User
import com.example.lab3.domain.port.UserRepositoryPort
import com.example.lab3.application.exception.NotFoundByIdException
import org.springframework.stereotype.Service

@Service
open class UserService(
    private val userRepository: UserRepositoryPort
) {
    fun create(user: User): CreateUserResult {
        val existing = userRepository.findByEmail(user.email)

        return if (existing != null) {
            CreateUserResult(existing, false)
        } else {
            val created = userRepository.create(user)
            CreateUserResult(created, true)
        }
    }

    fun getById(id: Long): User =
        userRepository.findById(id) ?: throw NotFoundByIdException("User", id)

    fun update(id: Long, updatedUser: User): User {
        val existingUser = userRepository.findById(id) ?: throw NotFoundByIdException("User", id)

        val userWithSameEmail = userRepository.findByEmail(updatedUser.email)
        if (userWithSameEmail != null && userWithSameEmail.id != id) {
            throw AlreadyExistsException("User", "email", updatedUser.email)
        }

        val userToSave = existingUser.copy(
            email = updatedUser.email,
            firstName = updatedUser.firstName,
            lastName = updatedUser.lastName,
            isActive = updatedUser.isActive
        )
        return userRepository.update(userToSave)
    }

    fun delete(id: Long) {
        val existingUser = userRepository.findById(id) ?: throw NotFoundByIdException("User", id)
        userRepository.delete(id)
    }

    fun getAll(): List<User> = userRepository.findAll()
}