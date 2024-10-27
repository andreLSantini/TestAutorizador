package com.caju.teste.autorizador.domain.service

import com.caju.teste.autorizador.adapter.output.response.AccountResponse
import com.caju.teste.autorizador.domain.model.Account
import com.caju.teste.autorizador.port.input.CreateAccountUseCase
import com.caju.teste.autorizador.port.input.GetAccontByIdUseCase
import com.caju.teste.autorizador.port.input.SaveAccountUseCase
import com.caju.teste.autorizador.port.output.AccountPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AccountService(val accountPort : AccountPort): GetAccontByIdUseCase, SaveAccountUseCase, CreateAccountUseCase {

    private val log = LoggerFactory.getLogger(AccountService::class.java)

    override fun execute(accountId: String): Account {
        log.info("Buscando account por id da account")
        val accountEntity = accountPort.findById(accountId)
        return accountEntity.toAccount()
    }

    override fun execute(account: Account): Account {
        log.info("salvando account")
        return accountPort.save(account);
    }

    override fun execute(): Account {
        log.info("Criando nova account")
        val accountEntity =  accountPort.createAccount();
        return accountEntity.toAccount()
    }
}