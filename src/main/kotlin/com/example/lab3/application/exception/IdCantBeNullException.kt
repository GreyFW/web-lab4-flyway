package com.example.lab3.application.exception

class IdCantBeNullException (
    entityName: String
) : IllegalArgumentException("$entityName id can't be null")
