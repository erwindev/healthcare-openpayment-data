package com.erwindev.openpayment.domain

/**
 * Created by erwinalberto on 1/6/18.
 */
data class OpenPayment(
        var id: Long? = null,
        var providerId: String,
        var providerName: String,
        var paymentAmount: Number,
        var payerId: String,
        var payerName: String
)