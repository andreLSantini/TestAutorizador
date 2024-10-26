package com.caju.teste.autorizador.domain.model

data class Account(
        val id: Long? = null,
        val accountId: String,
        val foodBalance: Double = 0.0,
        val mealBalance: Double = 0.0,
        val cashBalance: Double = 0.0
)

