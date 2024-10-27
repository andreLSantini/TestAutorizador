package com.caju.teste.autorizador.port.output

import com.caju.teste.autorizador.adapter.output.database.data.TransactionEntity

interface TransactionPort {
     fun findByIdempotencyId(idempotency: String) : TransactionEntity?
     fun saveTransaction(transactionEntity: TransactionEntity) : TransactionEntity
}