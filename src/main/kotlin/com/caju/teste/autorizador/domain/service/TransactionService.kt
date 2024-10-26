package com.caju.teste.autorizador.domain.service

import com.caju.teste.autorizador.adapter.output.response.TransactionResponse
import com.caju.teste.autorizador.domain.exception.AccountNotFoundException
import com.caju.teste.autorizador.domain.model.Account
import com.caju.teste.autorizador.domain.model.Transaction
import com.caju.teste.autorizador.port.input.AuthorizeTransactionUseCase
import com.caju.teste.autorizador.port.input.GetAccontByIdUseCase
import com.caju.teste.autorizador.port.output.TransactionPort
import org.springframework.stereotype.Service

@Service
class TransactionService(
        private val transactionPort: TransactionPort,
        private val getAccontByIdUseCase: GetAccontByIdUseCase) :
        AuthorizeTransactionUseCase {


    override fun execute(transaction: Transaction): TransactionResponse {

        val account = getAccontByIdUseCase.execute(accountId = transaction.accountId)


        val category = when (transaction.mcc) {
            "5411", "5412" -> "FOOD"
            "5811", "5812" -> "MEAL"
            else -> "CASH"
        }
        if (verifyBalanceAccount(transaction.totalAmount, account, category)) {
            println("")

        }

        return TransactionResponse("00");

        // L1
        // MAPEAR CONFORME O MCC E FAZER O DEBITO NO VALOR DA CATEGORIA.
        // L2 CASO A CATEGORIA NAO TIVER SALDO, TENTAR DEBITAR NA CATEGORIA CASH E RETORNE OK, COD 00
        //
    }

    private fun verifyBalanceAccount(totalAmount: Double, account: Account, category: String): Boolean {
        return when (category) {
            "FOOD" -> totalAmount <= account.foodBalance
            "MEAL" -> totalAmount <= account.mealBalance
            else -> totalAmount <= account.cashBalance
        }
    }


}