package com.caju.teste.autorizador.domain.model

class Transaction(
        val accountId: String,
        val totalAmount: Double,
        val mcc: String,
        val merchant: String
)