package com.example.lab3.adapter.web.controller

import com.example.lab3.adapter.web.dto.dish.DishCreateRequest
import com.example.lab3.adapter.web.dto.dish.DishResponse
import com.example.lab3.adapter.web.dto.restaurant.RestaurantCreateRequest
import com.example.lab3.adapter.web.dto.restaurant.RestaurantResponse
import com.example.lab3.adapter.web.dto.restaurant.RestaurantUpdateRequest
import com.example.lab3.adapter.web.mapper.DishMapper
import com.example.lab3.adapter.web.mapper.RestaurantMapper
import com.example.lab3.application.service.DishService
import com.example.lab3.application.service.RestaurantService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/restaurants")
class RestaurantController(
    private val restaurantService: RestaurantService,
    private val dishService: DishService
) {
    @PostMapping
    fun create(@Valid @RequestBody request: RestaurantCreateRequest): ResponseEntity<RestaurantResponse> {
        val restaurant = restaurantService.create(RestaurantMapper.toDomain(request))
        return ResponseEntity.status(HttpStatus.CREATED).body(RestaurantMapper.toResponse(restaurant))
    }

    @GetMapping
    fun getAll(): List<RestaurantResponse> =
        restaurantService.getAll().map { RestaurantMapper.toResponse(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): RestaurantResponse =
        RestaurantMapper.toResponse(restaurantService.getById(id))

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody request: RestaurantUpdateRequest
    ): RestaurantResponse =
        RestaurantMapper.toResponse(restaurantService.update(id, RestaurantMapper.toDomain(id, request)))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = restaurantService.delete(id)

    @GetMapping("/{id}/dishes")
    fun getDishes(@PathVariable id: Long): List<DishResponse> =
        restaurantService.getDishes(id).map { DishMapper.toResponse(it) }

    @PostMapping("/{restaurantId}/dishes")
    fun createDish(
        @PathVariable restaurantId: Long,
        @Valid @RequestBody request: DishCreateRequest
    ): ResponseEntity<DishResponse> {
        val result = dishService.create(DishMapper.toDomain(request, restaurantId))
        val response = DishMapper.toResponse(result.dish)
        return if (result.isCreated) ResponseEntity.status(HttpStatus.CREATED).body(response)
        else ResponseEntity.ok(response)
    }
}