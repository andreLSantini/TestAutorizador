package com.caju.teste.autorizador.integration

import com.caju.teste.autorizador.adapter.output.request.TransactionRequest
import com.caju.teste.autorizador.adapter.output.response.TransactionResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import kotlin.test.DefaultAsserter.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TransactionControllerIntegrationTest {

    @Autowired
    private lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun shouldRequestSuccess() {
        val trnsactionRequest = TransactionRequest(
                mcc = "",
                totalAmount = BigDecimal(100.00),
                merchant = "",
                account = ""
        )

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val request: HttpEntity<TransactionRequest> = HttpEntity(trnsactionRequest, headers)

        val response: ResponseEntity<TransactionResponse> = testRestTemplate
                .withBasicAuth("usuario", "123456")
                .exchange("/api/v1/transactions/authorize", HttpMethod.POST, request, TransactionResponse::class.java)


        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode())

        Assertions.assertNotNull(response.getBody())

    }
}