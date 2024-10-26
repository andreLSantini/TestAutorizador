package com.caju.teste.autorizador.adapter.exception.handler

import com.caju.teste.autorizador.adapter.output.response.TransactionResponse
import com.caju.teste.autorizador.domain.exception.AccountNotFoundException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ExceptionHandlerApi {


    @ExceptionHandler(AccountNotFoundException::class)
    fun handlerExceptionAccountNotFoundException(cartaoExistenteException: AccountNotFoundException?): ResponseEntity<Any?> {
        val transactionResponse = TransactionResponse("07")
        return ResponseEntity(transactionResponse, HttpStatus.OK)
    }
}
