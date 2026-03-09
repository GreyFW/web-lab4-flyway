package com.example.lab3.adapter.web.controller

import com.example.lab3.adapter.web.dto.user.*
import com.example.lab3.adapter.web.mapper.UserMapper
import com.example.lab3.application.service.UserService

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping
    fun create(
        @Valid @RequestBody request: UserCreateRequest
    ): ResponseEntity<UserResponse> {
        val result = userService.create( UserMapper.toDomain(request) )

        val response = UserMapper.toResponse(result.user)

        return if (result.isCreated) {
            ResponseEntity.status(HttpStatus.CREATED).body(response)
        } else {
            ResponseEntity.ok(response)
        }
    }

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: Long
    ): UserResponse {
        val user = userService.getById(id)

        return UserMapper.toResponse(user)
    }

    @GetMapping
    fun getAll(): List<UserResponse> =
        userService.getAll().map(UserMapper::toResponse)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody request: UserUpdateRequest
    ): UserResponse {
        val updated = userService.update(
            id, UserMapper.toDomain(id, request) )

        return UserMapper.toResponse(updated)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @PathVariable id: Long
    ) {
        userService.delete(id)
    }
}