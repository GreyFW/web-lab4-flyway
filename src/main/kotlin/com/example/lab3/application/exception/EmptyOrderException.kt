package com.example.lab3.application.exception

class EmptyOrderException(
    message: String = "Order must contain at least one dish"
) : RuntimeException(message)