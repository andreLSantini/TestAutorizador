package com.caju.teste.autorizador.adapter.output.database.repository

import com.caju.teste.autorizador.adapter.output.database.data.TransactionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransactionRepository: JpaRepository<TransactionEntity, String> {

    @Query("select t from TransactionEntity t where t.idempotency = ?1")
    fun findByIdempotency(idempotency: String): Optional<TransactionEntity>

}