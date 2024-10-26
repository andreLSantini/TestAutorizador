package com.caju.teste.autorizador.adapter.output.database.data

import com.caju.teste.autorizador.domain.model.Account
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "account")
data class AccountEntity(
        @Id
        @Column
        val accountId: String,
        @Column
        val foodBalance: Double = 0.0,
        @Column
        val mealBalance: Double = 0.0,
        @Column
        val cashBalance: Double = 0.0
) {
    fun toAccount(): Account {
        return Account(
                accountId = this.accountId,
                foodBalance = this.foodBalance,
                mealBalance = this.mealBalance,
                cashBalance = this.cashBalance
        )
    }
}
