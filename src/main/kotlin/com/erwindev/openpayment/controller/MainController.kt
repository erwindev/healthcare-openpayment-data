package com.erwindev.openpayment.controller

import com.erwindev.openpayment.domain.OpenPayment
import com.erwindev.openpayment.service.OpenPaymentService
import com.erwindev.openpayment.util.ApplicationSettings
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


/**
 * Created by erwinalberto on 1/5/18.
 */

@RestController
@Api(value = "openpayment", description = "OpenPayment API")
@RequestMapping(value="/api/openpayment/v1")
class MainController{

    @Autowired
    lateinit var applicationSettings: ApplicationSettings

    @Autowired
    lateinit var openPaymentService: OpenPaymentService

    @GetMapping("/payments")
    fun payments(): List<OpenPayment> {
        var payments: List<OpenPayment> = openPaymentService.findAllOpenPayments()
        return payments
    }

    @GetMapping("/provider-payment/{providerId}")
    fun providerPayments(@PathVariable(value="providerId") providerId: String): List<OpenPayment>{
        return openPaymentService.findProviderOpenPayments(providerId)
    }

    @GetMapping("/payer-payment/{payerId}")
    fun payerPayments(@PathVariable(value="payerId") payerId: String): List<OpenPayment>{
        return openPaymentService.findPayerOpenPayments(payerId)
    }
}