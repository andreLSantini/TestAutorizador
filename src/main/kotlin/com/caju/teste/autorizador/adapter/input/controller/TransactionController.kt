package com.caju.teste.autorizador.adapter.input.controller

import com.caju.teste.autorizador.adapter.output.request.TransactionRequest
import com.caju.teste.autorizador.adapter.output.response.TransactionResponse
import com.caju.teste.autorizador.port.input.AuthorizeTransactionUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/transactions")
class TransactionController(private val authorizeTransactionUseCase: AuthorizeTransactionUseCase) {

    @PostMapping("/authorize")
    fun authorizeTransaction(@RequestBody request: TransactionRequest): ResponseEntity<TransactionResponse> {
        val result = authorizeTransactionUseCase.authorizeTransaction(request)
        return ResponseEntity.ok(result)
    }
}