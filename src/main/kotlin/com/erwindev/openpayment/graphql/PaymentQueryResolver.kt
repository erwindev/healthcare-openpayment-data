package com.erwindev.openpayment.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.erwindev.openpayment.domain.OpenPayment
import com.erwindev.openpayment.service.OpenPaymentService
import org.springframework.stereotype.Component

@Component
class PaymentQueryResolver(private val paymentService: OpenPaymentService): GraphQLQueryResolver {
    fun getPayments(): List<OpenPayment> {
        return paymentService.findAllOpenPayments()
    }
}