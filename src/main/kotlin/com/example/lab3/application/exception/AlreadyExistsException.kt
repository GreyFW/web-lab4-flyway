package com.example.lab3.application.exception

class AlreadyExistsException(
    entityName: String,
    keyType: String,
    key: String
) : IllegalArgumentException("$entityName with $keyType=$key already exists")
