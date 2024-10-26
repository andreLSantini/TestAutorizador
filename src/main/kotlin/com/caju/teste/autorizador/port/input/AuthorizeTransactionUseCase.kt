package com.caju.teste.autorizador.port.input

import com.caju.teste.autorizador.adapter.output.response.TransactionResponse
import com.caju.teste.autorizador.domain.model.Transaction

interface AuthorizeTransactionUseCase {
    fun execute(transaction: Transaction): TransactionResponse
}