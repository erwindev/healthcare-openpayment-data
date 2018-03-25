package com.erwindev.openpayment.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.erwindev.openpayment.service.OpenPaymentService
import org.springframework.stereotype.Component

@Component
class PaymentQueryResolver(private val paymentService: OpenPaymentService): GraphQLQueryResolver {
    fun getPayments() = paymentService.findAllOpenPayments()
    fun getPayment(id: Long) = paymentService.findOpenPayment(id)
    fun getPaymentsByPayer(payerId: String) = paymentService.findPayerOpenPayments(payerId)
    fun getPaymentsToProviders(providerId: String) = paymentService.findProviderOpenPayments(providerId)
}