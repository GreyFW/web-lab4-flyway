package com.example.lab3.adapter.web.mapper

import com.example.lab3.adapter.web.dto.restaurant.RestaurantCreateRequest
import com.example.lab3.adapter.web.dto.restaurant.RestaurantResponse
import com.example.lab3.adapter.web.dto.restaurant.RestaurantUpdateRequest
import com.example.lab3.domain.model.Restaurant

object RestaurantMapper {
    fun toDomain(request: RestaurantCreateRequest) =
        Restaurant(id = null, name = request.name, address = request.address)

    fun toDomain(id: Long, request: RestaurantUpdateRequest) =
        Restaurant(id = id, name = request.name, address = request.address)

    fun toResponse(restaurant: Restaurant) =
        RestaurantResponse(id = restaurant.id!!, name = restaurant.name, address = restaurant.address)
}