package com.caju.teste.autorizador.port.input

import com.caju.teste.autorizador.adapter.output.request.TransactionRequest
import com.caju.teste.autorizador.adapter.output.response.TransactionResponse

interface AuthorizeTransactionUseCase {
    fun authorizeTransaction(request: TransactionRequest): TransactionResponse
}