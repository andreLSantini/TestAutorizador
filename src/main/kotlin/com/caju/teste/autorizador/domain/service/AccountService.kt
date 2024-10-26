package com.caju.teste.autorizador.domain.service

import com.caju.teste.autorizador.domain.exception.AccountNotFoundException
import com.caju.teste.autorizador.domain.model.Account
import com.caju.teste.autorizador.port.input.GetAccontByIdUseCase
import com.caju.teste.autorizador.port.input.SaveAccountUseCase
import com.caju.teste.autorizador.port.output.AccountPort
import org.springframework.stereotype.Service

@Service
class AccountService(val accountPort : AccountPort): GetAccontByIdUseCase, SaveAccountUseCase {
    override fun execute(accountId: String): Account {
        val accountEntity = accountPort.findById(accountId)
        return accountEntity.toAccount()
    }

    override fun execute(account: Account): Account {
        return accountPort.save(account);
    }
}