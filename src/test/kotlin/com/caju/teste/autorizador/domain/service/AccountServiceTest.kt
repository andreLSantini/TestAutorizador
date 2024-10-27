package com.caju.teste.autorizador.domain.service

import com.caju.teste.autorizador.adapter.output.database.data.AccountEntity
import com.caju.teste.autorizador.domain.model.Account
import com.caju.teste.autorizador.port.output.AccountPort
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class AccountServiceTest {

    private lateinit var accountService: AccountService
    private val accountPort: AccountPort = mockk()

    @BeforeEach
    fun setup() {
        accountService = AccountService(accountPort)
    }

    @Test
    fun `should retrieve account by accountId`() {
        val accountId = "123"
        val accountEntity = AccountEntity(accountId, BigDecimal(100), BigDecimal(50), BigDecimal(30))

        every { accountPort.findById(accountId) } returns accountEntity
        val account = accountService.execute(accountId)

        assertEquals(accountId, account.accountId)
        assertEquals(BigDecimal(30), account.cashBalance)
        verify { accountPort.findById(accountId) }
    }

    @Test
    fun `should save account successfully`() {
        val account = Account("123", BigDecimal(100), BigDecimal(50), BigDecimal(30))

        every { accountPort.save(account) } returns account

        val savedAccount = accountService.execute(account)

        assertEquals(account, savedAccount)
        verify { accountPort.save(account) }
    }

    @Test
    fun `should create a new account successfully`() {
        val newAccount = AccountEntity("456", BigDecimal(0), BigDecimal(0), BigDecimal(0))

        every { accountPort.createAccount() } returns newAccount

        val createdAccount = accountService.execute()

        assertEquals(newAccount.accountId, createdAccount.accountId)
        verify { accountPort.createAccount() }
    }
}
