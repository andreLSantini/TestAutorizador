package com.caju.teste.autorizador.adapter.output.database

import com.caju.teste.autorizador.adapter.output.database.data.AccountEntity
import com.caju.teste.autorizador.adapter.output.database.repository.AccountRepository
import com.caju.teste.autorizador.domain.exception.AccountNotFoundException
import com.caju.teste.autorizador.domain.model.Account
import com.caju.teste.autorizador.domain.service.AccountService
import com.caju.teste.autorizador.port.output.AccountPort
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.UUID

@Component
class AccountPersistence(val accountRepository: AccountRepository) : AccountPort {

    private val log = LoggerFactory.getLogger(AccountPersistence::class.java)

    @Transactional
    override fun findById(id: String): AccountEntity {
        log.info("Buscando AccountEntity por id da account")
        return accountRepository.findById(id).orElseThrow {
            log.info("Conta não encontrada")
            throw AccountNotFoundException("Conta não encontrada")
        }
    }

    @Transactional
    override fun save(account: Account): Account {
        log.info("salvando account")
        var accountEntity = AccountEntity(
                accountId = account.accountId,
                foodBalance = account.foodBalance,
                mealBalance = account.mealBalance,
                cashBalance = account.cashBalance,
        )
        accountRepository.save(accountEntity)
        return account;
    }

    override fun createAccount(): AccountEntity {
        log.info("Criando nova account")
        var account = AccountEntity(
                accountId = UUID.randomUUID().toString(),
                foodBalance = BigDecimal.valueOf(100.00),
                mealBalance = BigDecimal.valueOf(100.00),
                cashBalance = BigDecimal.valueOf(100.00),
        )
        return accountRepository.save(account)
    }
}