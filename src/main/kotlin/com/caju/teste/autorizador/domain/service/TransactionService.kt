package com.caju.teste.autorizador.domain.service

import com.caju.teste.autorizador.adapter.output.request.TransactionRequest
import com.caju.teste.autorizador.adapter.output.response.TransactionResponse
import com.caju.teste.autorizador.port.input.AuthorizeTransactionUseCase
import com.caju.teste.autorizador.port.output.TransactionPort
import org.springframework.stereotype.Service

@Service
class TransactionService(private val transactionPort: TransactionPort) : AuthorizeTransactionUseCase {
    override fun authorizeTransaction(request: TransactionRequest): TransactionResponse {
        TODO("Not yet implemented")
    }

}