package com.caju.teste.autorizador.adapter.output.database

import com.caju.teste.autorizador.adapter.output.database.data.AccountEntity
import com.caju.teste.autorizador.adapter.output.database.repository.AccountRepository
import com.caju.teste.autorizador.domain.exception.AccountNotFoundException
import com.caju.teste.autorizador.domain.model.Account
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import java.math.BigDecimal
import java.util.*

class AccountPersistenceTest {

    private val accountRepository: AccountRepository = mockk()
    private lateinit var accountPersistence: AccountPersistence

    @BeforeEach
    fun setup() {
        accountPersistence = AccountPersistence(accountRepository)
    }

    @Test
    fun `should find account by id`() {
        val accountId = UUID.randomUUID().toString()
        val accountEntity = AccountEntity(accountId, BigDecimal(100), BigDecimal(50), BigDecimal(30))
        every { accountRepository.findById(accountId) } returns Optional.of(accountEntity)

        val result = accountPersistence.findById(accountId)

        assertEquals(accountEntity, result)
        verify { accountRepository.findById(accountId) }
    }

    @Test
    fun `should throw AccountNotFoundException if account not found`() {
        val accountId = UUID.randomUUID().toString()
        every { accountRepository.findById(accountId) } returns Optional.empty()

        assertThrows(AccountNotFoundException::class.java) {
            accountPersistence.findById(accountId)
        }
    }

    @Test
    fun `should save account success`() {
        val account = Account(
                UUID.randomUUID().toString(), BigDecimal(100), BigDecimal(50), BigDecimal(30))
        val accountEntity = AccountEntity(account.accountId, account.foodBalance, account.mealBalance, account.cashBalance)

        every { accountRepository.save(any<AccountEntity>()) } returns accountEntity

        val result = accountPersistence.save(account)

        assertEquals(account, result)
        verify { accountRepository.save(any<AccountEntity>()) }
    }

    @Test
    fun `should create a new account with default balances`() {
        val accountEntity = AccountEntity(
                UUID.randomUUID().toString(), BigDecimal(100), BigDecimal(100), BigDecimal(100))
        every { accountRepository.save(any<AccountEntity>()) } returns accountEntity

        val result = accountPersistence.createAccount()

        assertEquals(BigDecimal(100), result.cashBalance)
        assertEquals(BigDecimal(100), result.foodBalance)
        assertEquals(BigDecimal(100), result.mealBalance)
        verify { accountRepository.save(any<AccountEntity>()) }
    }
}