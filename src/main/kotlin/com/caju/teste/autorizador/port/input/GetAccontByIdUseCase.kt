package com.caju.teste.autorizador.port.input

import com.caju.teste.autorizador.domain.model.Account

interface GetAccontByIdUseCase {

    fun execute(accountId : String) : Account
}