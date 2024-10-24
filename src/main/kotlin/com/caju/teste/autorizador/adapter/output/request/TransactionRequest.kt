package com.caju.teste.autorizador.adapter.output.request

data class TransactionRequest(
        val account: String,
        val totalAmount: Double,
        val mcc: String,
        val merchant: String
)