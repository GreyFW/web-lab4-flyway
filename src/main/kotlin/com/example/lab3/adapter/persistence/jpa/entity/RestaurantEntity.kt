package com.example.lab3.adapter.persistence.jpa.entity

import com.example.lab3.domain.model.Restaurant
import jakarta.persistence.*

@Entity
@Table(name = "restaurants")
class RestaurantEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String = "",

    @Column(nullable = false)
    val address: String = "",

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    val dishes: MutableList<DishEntity> = mutableListOf()
) {
    fun toDomain() = Restaurant(id, name, address)

    companion object {
        fun fromDomain(restaurant: Restaurant) = RestaurantEntity(
            id = restaurant.id ?: 0,
            name = restaurant.name,
            address = restaurant.address
        )
    }
}