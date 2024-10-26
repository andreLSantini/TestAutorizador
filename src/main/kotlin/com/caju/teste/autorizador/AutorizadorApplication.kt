package com.caju.teste.autorizador

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class AutorizadorApplication

fun main(args: Array<String>) {
	runApplication<AutorizadorApplication>(*args)
}
