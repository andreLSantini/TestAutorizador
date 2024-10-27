package com.caju.teste.autorizador.adapter.input.controller

import com.caju.teste.autorizador.adapter.output.request.TransactionRequest
import com.caju.teste.autorizador.adapter.output.response.TransactionResponse
import com.caju.teste.autorizador.domain.model.Transaction
import com.caju.teste.autorizador.port.input.AuthorizeTransactionUseCase
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/transactions")
class TransactionController(private val authorizeTransactionUseCase: AuthorizeTransactionUseCase) {

    private val log = LoggerFactory.getLogger(TransactionController::class.java)

    @PostMapping("/authorize")
    fun authorizeTransaction(@RequestBody request: TransactionRequest): ResponseEntity<TransactionResponse> {
        log.info("Request: Iniciando transação para a conta: ${request.account}")
        val result = authorizeTransactionUseCase.execute(createTransactionEntity(request))
        return ResponseEntity.ok(result)
    }

    private fun createTransactionEntity(request: TransactionRequest): Transaction {
        return Transaction(
                accountId = request.account,
                mcc = request.mcc,
                merchant = request.merchant,
                totalAmount = request.totalAmount
        )
    }
}