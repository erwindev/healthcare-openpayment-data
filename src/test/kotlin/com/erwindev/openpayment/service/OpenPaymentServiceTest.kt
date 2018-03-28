package com.erwindev.openpayment.service

import com.erwindev.openpayment.domain.OpenPayment
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by erwinalberto on 1/6/18.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
@TestPropertySource(locations=arrayOf("classpath:application-test.properties"))
class OpenPaymentServiceTest {
    @Autowired
    lateinit var openPaymentService : OpenPaymentService

    @Test
    fun testAddOpenPayment(){
        var openPayment: OpenPayment =
                OpenPayment(id=1,
                        providerId = "1234",
                        providerName = "Dr. Jack",
                        paymentAmount = "94.30".toFloat(),
                        payerId = "12399393",
                        payerName = "Walgreens")

        openPaymentService.addOpenPayment(openPayment)

        var returnedOpenPayment : OpenPayment = openPaymentService.findOpenPayment(1)

        assertEquals(openPayment.id, returnedOpenPayment.id)
    }
}