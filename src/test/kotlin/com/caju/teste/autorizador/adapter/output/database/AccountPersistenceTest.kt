package com.caju.teste.autorizador.adapter.output.database

import com.caju.teste.autorizador.adapter.output.database.repository.AccountRepository
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class AccountPersistenceTest {

    private val accountRepository: AccountRepository = mockk()
    private lateinit var accountPersistence: AccountPersistence

    @Test
    fun findById() {
    }

    @Test
    fun save() {
    }

    @Test
    fun createAccount() {
    }

    @Test
    fun getAccountRepository() {
    }
}