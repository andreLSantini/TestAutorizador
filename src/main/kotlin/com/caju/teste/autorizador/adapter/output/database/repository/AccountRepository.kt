package com.caju.teste.autorizador.adapter.output.database.repository

import com.caju.teste.autorizador.adapter.output.database.data.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: JpaRepository<AccountEntity, String>  {
}
