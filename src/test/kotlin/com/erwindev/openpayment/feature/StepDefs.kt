package com.erwindev.openpayment.feature

import com.erwindev.openpayment.Application
import com.erwindev.openpayment.domain.OpenPayment
import com.erwindev.openpayment.service.OpenPaymentService
import cucumber.api.java8.En
import org.junit.Assert

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

/**
 * Created by erwinalberto on 3/5/18.
 */


@SpringBootTest
@TestPropertySource(locations=arrayOf("classpath:application-test.properties"))
class StepDefs : En {

    @Autowired
    lateinit var openPaymentService: OpenPaymentService

    lateinit var providerId: String
    lateinit var providers: List<OpenPayment>

    init{

        Given("^^I have \\\"([^\\\"]*)\\\" provider id$"){ _providerId: String ->
            providerId = _providerId
        }

        When("^I ask for all the payments$"){
            providers = openPaymentService.findProviderOpenPayments(providerId)
        }

        Then("^I receive (\\d+) payments$"){ paymentSize: Int ->
            Assert.assertEquals(paymentSize, providers.size)
        }
    }
}