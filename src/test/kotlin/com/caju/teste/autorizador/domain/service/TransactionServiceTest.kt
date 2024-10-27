package com.caju.teste.autorizador.domain.service

import com.caju.teste.autorizador.adapter.output.database.data.TransactionEntity
import com.caju.teste.autorizador.domain.model.Account
import com.caju.teste.autorizador.domain.model.Transaction
import com.caju.teste.autorizador.port.input.GetAccontByIdUseCase
import com.caju.teste.autorizador.port.input.SaveAccountUseCase
import com.caju.teste.autorizador.port.output.TransactionPort
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class TransactionServiceTest {


    private lateinit var transactionEntity: TransactionEntity
    private lateinit var account: Account
    private val transactionPort: TransactionPort = mockk()
    private val getAccountByIdUseCase: GetAccontByIdUseCase = mockk()
    private val saveAccountUseCase: SaveAccountUseCase = mockk()
    private lateinit var transactionService: TransactionService

    @BeforeEach
    fun setup() {
        transactionService = TransactionService(
                transactionPort, getAccountByIdUseCase, saveAccountUseCase)
        account = Account(
                accountId = "123",
                foodBalance = BigDecimal(50.0),
                mealBalance = BigDecimal(20.0),
                cashBalance = BigDecimal(1000.0)
        )
        transactionEntity = TransactionEntity(accountId = "123",
                totalAmount = BigDecimal(10.0),
                mcc = "5411",
                merchant = "UBER EATS",
                idempotency = "")
        every { transactionPort.findByIdempotencyId(any()) } returns null
        every { getAccountByIdUseCase.execute("123") } returns account
        every { saveAccountUseCase.execute(any()) } returns account
        every { transactionPort.saveTransaction(any()) } returns transactionEntity
    }

    //TESTES L1
    @Test
    fun `should return success with code 00 mcc FOOD`() {
        val transaction = Transaction(
                accountId = "123",
                totalAmount = BigDecimal(10.0),
                mcc = "5411",
                merchant = "*******"
        )

        val response = transactionService.execute(transaction)

        assertEquals("00", response.code)
        verify { saveAccountUseCase.execute(account) }
        verify { transactionPort.saveTransaction(any()) }
    }

    @Test
    fun `should return success with code 51 mcc FOOD`() {
        val transaction = Transaction(
                accountId = "123",
                totalAmount = BigDecimal(1510.0),
                mcc = "5411",
                merchant = "*******"
        )

        val response = transactionService.execute(transaction)

        assertEquals("51", response.code)
    }

    @Test
    fun `should return success with code 00 mcc MEAL`() {
        val transaction = Transaction(
                accountId = "123",
                totalAmount = BigDecimal(10.0),
                mcc = "5811",
                merchant = "*******"
        )

        val response = transactionService.execute(transaction)

        assertEquals("00", response.code)
        verify { saveAccountUseCase.execute(account) }
        verify { transactionPort.saveTransaction(any()) }
    }

    @Test
    fun `should return success with code 51 mcc MEAL`() {
        val transaction = Transaction(
                accountId = "123",
                totalAmount = BigDecimal(1510.0),
                mcc = "5811",
                merchant = "*******"
        )
        val response = transactionService.execute(transaction)
        assertEquals("51", response.code)
    }

    @Test
    fun `should return success with code 00 mcc CASH`() {
        val transaction = Transaction(
                accountId = "123",
                totalAmount = BigDecimal(10.0),
                mcc = "1111",
                merchant = "*******"
        )

        val response = transactionService.execute(transaction)

        assertEquals("00", response.code)
        verify { saveAccountUseCase.execute(account) }
        verify { transactionPort.saveTransaction(any()) }
    }

    @Test
    fun `should return success with code 51 mcc CASH`() {
        val transaction = Transaction(
                accountId = "123",
                totalAmount = BigDecimal(1510.0),
                mcc = "1111",
                merchant = "*******"
        )
        val response = transactionService.execute(transaction)
        assertEquals("51", response.code)
    }

    //TESTES L2
    @Test
    fun `should return success with code 00 mcc FOOD using CASH`() {
        val transaction = Transaction(
                accountId = "123",
                totalAmount = BigDecimal(500.0),
                mcc = "5412",
                merchant = "*******"
        )

        val response = transactionService.execute(transaction)

        assertEquals("00", response.code)
        verify { saveAccountUseCase.execute(account) }
        verify { transactionPort.saveTransaction(any()) }
    }

    @Test
    fun `should return success with code 00 mcc MEAL using CASH`() {
        val transaction = Transaction(
                accountId = "123",
                totalAmount = BigDecimal(500.0),
                mcc = "5812",
                merchant = "*******"
        )

        val response = transactionService.execute(transaction)

        assertEquals("00", response.code)
        verify { saveAccountUseCase.execute(account) }
        verify { transactionPort.saveTransaction(any()) }
    }

    //TESTES L3
    @Test
    fun `should return success with code 00 mcc incorrect using name merchant uber trip`() {
        val transaction = Transaction(
                accountId = "123",
                totalAmount = BigDecimal(10.0),
                mcc = "111",
                merchant = "UBER TRIP"
        )

        val response = transactionService.execute(transaction)

        assertEquals("00", response.code)
        verify { saveAccountUseCase.execute(account) }
        verify { transactionPort.saveTransaction(any()) }
    }

    @Test
    fun `should return success with code 00 mcc incorrect using name merchant uber eats`() {
        val transaction = Transaction(
                accountId = "123",
                totalAmount = BigDecimal(10.0),
                mcc = "111",
                merchant = "UBER EATS"
        )

        val response = transactionService.execute(transaction)

        assertEquals("00", response.code)
        verify { saveAccountUseCase.execute(account) }
        verify { transactionPort.saveTransaction(any()) }
    }

    @Test
    fun `should return success with code 00 mcc incorrect using name merchant pag`() {
        val transaction = Transaction(
                accountId = "123",
                totalAmount = BigDecimal(10.0),
                mcc = "111",
                merchant = "PAG*"
        )

        val response = transactionService.execute(transaction)

        assertEquals("00", response.code)
        verify { saveAccountUseCase.execute(account) }
        verify { transactionPort.saveTransaction(any()) }
    }

    @Test
    fun `should return success with code 00 mcc incorrect using name merchant picpay`() {
        val transaction = Transaction(
                accountId = "123",
                totalAmount = BigDecimal(10.0),
                mcc = "111",
                merchant = "PICPAY*"
        )

        val response = transactionService.execute(transaction)

        assertEquals("00", response.code)
        verify { saveAccountUseCase.execute(account) }
        verify { transactionPort.saveTransaction(any()) }
    }

    //TESTES L4
    @Test
    fun `should return fail with code 07 existing transction`() {
        val transaction = Transaction(
                accountId = "123",
                totalAmount = BigDecimal(10.0),
                mcc = "111",
                merchant = "PICPAY*"
        )

        transactionService.execute(transaction)

        every { transactionPort.findByIdempotencyId(any()) } returns transactionEntity
        val response2 = transactionService.execute(transaction)

        assertEquals("07", response2.code)
        verify { saveAccountUseCase.execute(account) }
        verify { transactionPort.saveTransaction(any()) }
    }


}