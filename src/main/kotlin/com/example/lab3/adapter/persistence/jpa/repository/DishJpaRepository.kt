package com.example.lab3.adapter.persistence.jpa.repository

import com.example.lab3.adapter.persistence.jpa.entity.DishEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DishJpaRepository : JpaRepository<DishEntity, Long> {
    @Query("SELECT d FROM DishEntity d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :namePart, '%'))")
    fun findAllByNamePart(@Param("namePart") namePart: String): List<DishEntity>

    fun findByName(name: String): DishEntity?
}
