package com.caju.teste.autorizador.domain.service

import com.caju.teste.autorizador.adapter.input.controller.TransactionController
import com.caju.teste.autorizador.adapter.output.database.data.TransactionEntity
import com.caju.teste.autorizador.adapter.output.response.TransactionResponse
import com.caju.teste.autorizador.domain.model.Account
import com.caju.teste.autorizador.domain.model.Transaction
import com.caju.teste.autorizador.port.input.AuthorizeTransactionUseCase
import com.caju.teste.autorizador.port.input.GetAccontByIdUseCase
import com.caju.teste.autorizador.port.input.SaveAccountUseCase
import com.caju.teste.autorizador.port.input.SaveTransactionUseCase
import com.caju.teste.autorizador.port.output.TransactionPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class TransactionService(
        private val transactionPort: TransactionPort,
        private val getAccontByIdUseCase: GetAccontByIdUseCase,
        private val saveAccountUseCase: SaveAccountUseCase
) :
        AuthorizeTransactionUseCase, SaveTransactionUseCase {

    private val log = LoggerFactory.getLogger(TransactionService::class.java)

    private fun verifyCashBalanceAccount(totalAmount: BigDecimal, account: Account): Boolean {
        return (totalAmount <= account.cashBalance)
    }

    override fun execute(transaction: Transaction): TransactionResponse {
        log.info("Executando regras de validacao do tipo de transacao")
        if (verifyExistsTransaction(transaction)) {
            return TransactionResponse("07");
        }
        val account = getAccontByIdUseCase.execute(accountId = transaction.accountId)
        val category = getCategory(transaction)

        if (verifyBalanceAccount(transaction.totalAmount, account, category)) {
            val debitAccount = debitAccount(transaction.totalAmount, account, category)
            saveAccountUseCase.execute(debitAccount)

        } else if (verifyCashBalanceAccount(transaction.totalAmount, account)) {
            val debitAccount = debitAccount(transaction.totalAmount, account, "CASH")
            saveAccountUseCase.execute(debitAccount)
        } else {
            return TransactionResponse("51");
        }
        executeSave(transaction)
        return TransactionResponse("00");
    }

    private fun verifyExistsTransaction(transaction: Transaction): Boolean {
        var idempotency = generateIdempotencyId(transaction);
        return transactionPort.findByIdempotencyId(idempotency) != null
    }

    private fun generateIdempotencyId(transaction: Transaction): String {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm")
        val currentTime = LocalDateTime.now().format(formatter)
        return "${transaction.accountId}-${transaction.totalAmount}-${transaction.mcc}-${transaction.merchant}-$currentTime"
    }

    private fun getCategory(transaction: Transaction): String {
        val category = when {
            transaction.merchant.contains("UBER EATS", ignoreCase = true) -> "FOOD"
            transaction.merchant.contains("UBER TRIP", ignoreCase = true) -> "MEAL"
            transaction.merchant.contains("PAG*", ignoreCase = true) -> "CASH"
            transaction.merchant.contains("PICPAY*", ignoreCase = true) -> "CASH"
            else -> null
        }
        return category ?: when (transaction.mcc) {
            "5411", "5412" -> "FOOD"
            "5811", "5812" -> "MEAL"
            else -> "CASH"
        }
    }

    private fun verifyBalanceAccount(totalAmount: BigDecimal, account: Account, category: String): Boolean {
        return when (category) {
            "FOOD" -> totalAmount <= account.foodBalance
            "MEAL" -> totalAmount <= account.mealBalance
            else -> totalAmount <= account.cashBalance
        }
    }

    private fun debitAccount(totalAmount: BigDecimal, account: Account, category: String): Account {
        when (category) {
            "FOOD" -> account.foodBalance = account.foodBalance.subtract(totalAmount)
            "MEAL" -> account.mealBalance = account.mealBalance.subtract(totalAmount)
            else -> account.cashBalance = account.cashBalance.subtract(totalAmount)
        }
        return account
    }

    override fun executeSave(transaction: Transaction): Transaction {
        var idempotencyId = generateIdempotencyId(transaction)
        var transactionEntity = TransactionEntity(
                accountId = transaction.accountId,
                mcc = transaction.mcc,
                totalAmount = transaction.totalAmount,
                merchant = transaction.merchant,
                idempotency = idempotencyId
        )
        transactionPort.saveTransaction(transactionEntity)
        return transaction;
    }


}