package com.example.lab3.adapter.web.controller

import com.example.lab3.adapter.web.dto.dish.*
import com.example.lab3.adapter.web.mapper.DishMapper
import com.example.lab3.application.service.DishService

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/dishes")
class DishController (
    private val dishService: DishService
) {
    @PostMapping
    fun create(
        @Valid @RequestBody request: DishCreateRequest
    ): ResponseEntity<DishResponse> {
        val result = dishService.create( DishMapper.toDomain(request) )
        val response = DishMapper.toResponse(result.dish)

        return if (result.isCreated) {
            ResponseEntity.status(HttpStatus.CREATED).body(response)
        } else {
            ResponseEntity.ok(response)
        }
    }

    @GetMapping
    fun getAll(
        @RequestParam(required = false) namePart: String?
    ): List<DishResponse> = dishService.getAll(namePart)
                                        .map { DishMapper.toResponse(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): DishResponse {
        val dish = dishService.getById(id)
        return DishMapper.toResponse(dish)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody request: DishUpdateRequest
    ): DishResponse {
        val updated = dishService.update(
            id,
            DishMapper.toDomain(id, request)
        )
        return DishMapper.toResponse(updated)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        dishService.delete(id)
    }
}