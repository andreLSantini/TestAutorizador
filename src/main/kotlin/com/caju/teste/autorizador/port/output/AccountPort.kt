package com.caju.teste.autorizador.port.output

import com.caju.teste.autorizador.adapter.output.database.data.AccountEntity
import com.caju.teste.autorizador.domain.model.Account

interface AccountPort {
    fun findById(id: String): AccountEntity
    fun save(account: Account): Account
}