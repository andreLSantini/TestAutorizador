package com.caju.teste.autorizador.port.input

import com.caju.teste.autorizador.domain.model.Transaction

interface SaveTransactionUseCase {
    fun executeSave(transaction: Transaction): Transaction
}