package com.example.lab3.adapter.persistence.jpa.entity

import com.example.lab3.domain.model.User
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val email: String = "",

    @Column(nullable = false)
    val firstName: String = "",

    @Column(nullable = false)
    val lastName: String = "",

    @Column(nullable = false)
    val isActive: Boolean = true
) {
    fun toDomain() = User(id, email, firstName, lastName, isActive)

    companion object {
        fun fromDomain(user: User) = UserEntity(
            id = user.id ?: 0,
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            isActive = user.isActive
        )
    }
}