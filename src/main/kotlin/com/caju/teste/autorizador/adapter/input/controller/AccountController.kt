package com.caju.teste.autorizador.adapter.input.controller

import com.caju.teste.autorizador.adapter.output.response.AccountResponse
import com.caju.teste.autorizador.domain.model.Account
import com.caju.teste.autorizador.port.input.CreateAccountUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal


@RestController
@RequestMapping("/api/v1/account")
class AccountController(private val createAccountUseCase: CreateAccountUseCase) {

    @PostMapping
    fun createAccount(): ResponseEntity<AccountResponse> {
        val account = createAccountUseCase.execute()
        return ResponseEntity.ok(AccountResponse(account))
    }

    private fun AccountResponse(account: Account): AccountResponse {
        return AccountResponse(
                accountId = account.accountId,
                cashBalance = account.cashBalance,
                foodBalance = account.foodBalance,
                mealBalance = account.mealBalance
        )
    }
}