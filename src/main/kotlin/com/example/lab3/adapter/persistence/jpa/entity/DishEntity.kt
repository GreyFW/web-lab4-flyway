package com.example.lab3.adapter.persistence.jpa.entity

import com.example.lab3.domain.model.Dish
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "dishes")
data class DishEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String = "",

    @Column(nullable = false)
    val description: String = "",

    @Column(nullable = false)
    val price: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val isAvailable: Boolean = true
) {
    fun toDomain() = Dish(id, name, description, price, isAvailable)

    companion object {
        fun fromDomain(dish: Dish) = DishEntity(
            id = dish.id ?: 0,
            name = dish.name,
            description = dish.description,
            price = dish.price,
            isAvailable = dish.isAvailable
        )
    }
}
