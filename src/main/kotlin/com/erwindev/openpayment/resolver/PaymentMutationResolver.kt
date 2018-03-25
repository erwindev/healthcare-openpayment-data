package com.erwindev.openpayment.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.erwindev.openpayment.domain.OpenPayment
import com.erwindev.openpayment.service.OpenPaymentService
import org.springframework.stereotype.Component

@Component
class PaymentMutationResolver(private val paymentService: OpenPaymentService): GraphQLMutationResolver {
    fun newPayment(providerId: String,
                   providerName: String,
                   paymentAmount: Float,
                   payerId: String,
                   payerName: String): OpenPayment{

        var openPayment: OpenPayment = OpenPayment(providerId = providerId,
                providerName = providerName,
                paymentAmount = paymentAmount,
                payerId = payerId,
                payerName = payerName)

        return paymentService.addOpenPayment(openPayment)
    }
}