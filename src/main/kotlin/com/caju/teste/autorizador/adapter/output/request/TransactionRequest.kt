package com.caju.teste.autorizador.adapter.output.request

import java.math.BigDecimal

data class TransactionRequest(
        val account : String,
        val totalAmount: BigDecimal,
        val mcc: String,
        val merchant: String
)