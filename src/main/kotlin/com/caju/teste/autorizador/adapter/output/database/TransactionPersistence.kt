package com.caju.teste.autorizador.adapter.output.database

import com.caju.teste.autorizador.adapter.output.database.data.TransactionEntity
import com.caju.teste.autorizador.adapter.output.database.repository.TransactionRepository
import com.caju.teste.autorizador.port.output.TransactionPort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class TransactionPersistence(val transactionRepository: TransactionRepository) : TransactionPort {
    @Transactional
    override fun findByIdempotencyId(idempotency: String): TransactionEntity? {
        return transactionRepository.findByIdempotency(idempotency).getOrNull()
    }

    override fun saveTransaction(transactionEntity: TransactionEntity): TransactionEntity {
        return transactionRepository.save(transactionEntity)
    }
}