package com.caju.teste.autorizador.adapter.output.response

import java.math.BigDecimal


data class AccountResponse(
        val accountId: String,
        val foodBalance: BigDecimal = BigDecimal.ZERO,
        val mealBalance: BigDecimal = BigDecimal.ZERO,
        val cashBalance: BigDecimal = BigDecimal.ZERO
)