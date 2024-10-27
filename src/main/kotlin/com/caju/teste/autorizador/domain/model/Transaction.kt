package com.caju.teste.autorizador.domain.model

import java.math.BigDecimal

class Transaction(
        val accountId: String,
        val totalAmount: BigDecimal,
        val mcc: String,
        val merchant: String
)