package com.erwindev.openpayment.controller

import com.erwindev.openpayment.domain.OpenPayment
import com.erwindev.openpayment.service.OpenPaymentService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


/**
 * Created by erwinalberto on 1/6/18.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
@WebAppConfiguration
@TestPropertySource(locations=arrayOf("classpath:application-test.properties"))
class WebLayerTest {
    private var mvc:MockMvc? = null

    @Autowired
    lateinit var context: WebApplicationContext

    @Autowired
    lateinit var openPaymentService : OpenPaymentService

    @Before
    fun setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build()

        var openPayment: OpenPayment =
                OpenPayment(id=1,
                        providerId = "1234",
                        providerName = "Dr. Jack",
                        paymentAmount = "94.30".toFloat(),
                        payerId = "12399393",
                        payerName = "Walgreens")

        openPaymentService.addOpenPayment(openPayment)

    }

    @Test
    fun testGetPayments(){
        val result = mvc?.perform(get("/api/openpayment/v1/payments"))?.andExpect(status().isOk)?.andReturn()

        val content : String? = result?.response?.contentAsString

        val gson = Gson()

        val openPayments : List<OpenPayment> = gson.fromJson(content, object : TypeToken<List<OpenPayment>>() {}.type)

        assertEquals(openPayments[0].id, "1".toLong())

    }
}