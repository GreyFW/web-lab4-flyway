package com.example.lab3.adapter.web.controller

import com.example.lab3.adapter.web.dto.dish.*
import com.example.lab3.adapter.web.mapper.DishMapper
import com.example.lab3.application.service.DishService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/dishes")
class DishController(
    private val dishService: DishService
) {
    @GetMapping
    fun getAll(
        @RequestParam(required = false) namePart: String?
    ): List<DishResponse> =
        dishService.getAll(namePart).map { DishMapper.toResponse(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): DishResponse =
        DishMapper.toResponse(dishService.getById(id))

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody request: DishUpdateRequest
    ): DishResponse {
        val existing = dishService.getById(id)  // берём restaurantId из существующего блюда
        val updated = dishService.update(id, DishMapper.toDomain(id, request, existing.restaurantId))
        return DishMapper.toResponse(updated)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        dishService.delete(id)
    }
}