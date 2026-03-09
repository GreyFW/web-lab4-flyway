package com.example.lab3.adapter.web.controller

import com.example.lab3.adapter.web.dto.order.OrderCreateRequest
import com.example.lab3.adapter.web.dto.order.OrderResponse
import com.example.lab3.adapter.web.dto.order.OrderStatusUpdateRequest
import com.example.lab3.adapter.web.mapper.OrderMapper
import com.example.lab3.application.service.OrderService
import com.example.lab3.domain.model.OrderStatus
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/orders")
class OrderController(
    private val orderService: OrderService
) {
    @PostMapping
    fun create(@Valid @RequestBody request: OrderCreateRequest): ResponseEntity<OrderResponse> {
        val order = orderService.create(request.userId, request.dishIds)
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderMapper.toResponse(order))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): OrderResponse =
        OrderMapper.toResponse(orderService.getById(id))

    @GetMapping
    fun getAll(
        @RequestParam(required = false) userId: Long?,
        @RequestParam(required = false) status: OrderStatus?
    ): List<OrderResponse> =
        orderService.getAll(userId, status).map { OrderMapper.toResponse(it) }

    @PatchMapping("/{id}/status")
    fun updateStatus(
        @PathVariable id: Long,
        @Valid @RequestBody request: OrderStatusUpdateRequest
    ): OrderResponse =
        OrderMapper.toResponse(orderService.updateStatus(id, request.status))
}