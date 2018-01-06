package com.erwindev.openpayment.dao

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
class OpenPaymentDaoTest {

    @Autowired
    lateinit var openPaymentDao: OpenPaymentDao

    @Test
    fun testCreateOpenPayment(){
        var openPayment: OpenPayment =
                OpenPayment(providerId = "1234",
                        providerName = "Dr. Jack",
                        paymentAmount = 94.30,
                        payerId = "12399393",
                        payerName = "Walgreens")

        openPaymentDao.insert(openPayment)

        var savedOpenPayment = openPaymentDao.findById(1)

        assertEquals(openPayment.id, savedOpenPayment.id)
    }

}