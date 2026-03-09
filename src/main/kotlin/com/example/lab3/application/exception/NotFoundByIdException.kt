package com.example.lab3.application.exception

class NotFoundByIdException(
    entityName: String,
    id: Long
) : RuntimeException("$entityName with id=$id not found")