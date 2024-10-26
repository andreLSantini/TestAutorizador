package com.caju.teste.autorizador.adapter.output.database

import com.caju.teste.autorizador.adapter.output.database.data.AccountEntity
import com.caju.teste.autorizador.adapter.output.database.repository.AccountRepository
import com.caju.teste.autorizador.domain.exception.AccountNotFoundException
import com.caju.teste.autorizador.domain.model.Account
import com.caju.teste.autorizador.port.output.AccountPort
import org.springframework.stereotype.Component

@Component
class AccountPersistence(val accountRepository: AccountRepository) : AccountPort {
    override fun findById(id: String): AccountEntity {
        return accountRepository.findById(id).orElseThrow {
            throw AccountNotFoundException("Conta não encontrada")
        }
    }

    override fun save(account: Account): Account {
        TODO("Not yet implemented")
    }
}