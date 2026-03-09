package com.example.lab3.adapter.persistence.jpa.entity

import com.example.lab3.domain.model.Order
import com.example.lab3.domain.model.OrderStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
@NamedEntityGraph(
    name = "Order.withUserAndDishes",
    attributeNodes = [
        NamedAttributeNode("user"),
        NamedAttributeNode("dishes")
    ]
)
class OrderEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity = UserEntity(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: OrderStatus = OrderStatus.PENDING,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "order_dishes",
        joinColumns = [JoinColumn(name = "order_id")],
        inverseJoinColumns = [JoinColumn(name = "dish_id")]
    )
    val dishes: MutableList<DishEntity> = mutableListOf()
) {
    fun toDomain() = Order(
        id = id,
        userId = user.id,
        status = status,
        createdAt = createdAt,
        dishes = dishes.map { it.toDomain() }
    )
}