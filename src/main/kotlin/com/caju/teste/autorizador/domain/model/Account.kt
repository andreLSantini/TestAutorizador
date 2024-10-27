package com.caju.teste.autorizador.domain.model

import java.math.BigDecimal

data class Account(
        val accountId: String,
        var foodBalance: BigDecimal = BigDecimal.ZERO,
        var mealBalance: BigDecimal = BigDecimal.ZERO,
        var cashBalance: BigDecimal = BigDecimal.ZERO
)

