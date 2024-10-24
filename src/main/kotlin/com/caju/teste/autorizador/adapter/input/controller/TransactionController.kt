package com.caju.teste.autorizador.adapter.input.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/transactions")
class TransactionController {

    @PostMapping("/authorize")
    fun authorizeTransaction(): String {
        return "OK"
    }
}