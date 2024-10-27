package com.caju.teste.autorizador.integration

import com.caju.teste.autorizador.adapter.output.request.TransactionRequest
import com.caju.teste.autorizador.adapter.output.response.AccountResponse
import com.caju.teste.autorizador.adapter.output.response.TransactionResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TransactionControllerIntegrationTest {

    @Autowired
    private lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun `should Request Success to authorize`() {

        val responseAccount : ResponseEntity<AccountResponse> = testRestTemplate
                .withBasicAuth("usuario", "123456")
                .exchange("/api/v1/account", HttpMethod.POST, null,  AccountResponse::class.java)
        var accountResponse  = responseAccount.body;
        val transactionRequest = TransactionRequest(
                mcc = "5811",
                totalAmount = BigDecimal(20.00),
                merchant = "PADARIA DO ZE               SAO PAULO BR",
                account = accountResponse?.accountId!!
        )

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val request: HttpEntity<TransactionRequest> = HttpEntity(transactionRequest, headers)

        val response: ResponseEntity<TransactionResponse> = testRestTemplate
                .withBasicAuth("usuario", "123456")
                .exchange("/api/v1/transactions/authorize", HttpMethod.POST, request, TransactionResponse::class.java)


        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode())

        Assertions.assertNotNull(response.getBody())
        Assertions.assertEquals("00", response.body?.code ?: "")
    }
}