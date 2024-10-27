package com.caju.teste.autorizador.adapter.output.database.data

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "transactions")
data class TransactionEntity(
        @Id
        @Column
        val accountId: String,
        @Column
        val totalAmount: BigDecimal,
        @Column
        val mcc: String,
        @Column
        val merchant: String,
        @Column
        val idempotency: String
)
