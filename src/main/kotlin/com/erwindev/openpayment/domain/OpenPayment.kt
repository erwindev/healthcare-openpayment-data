package com.erwindev.openpayment.domain

/**
 * Created by erwinalberto on 1/6/18.
 */
data class OpenPayment(
        val id: Long? = null,
        val providerId: String,
        val providerName: String,
        val paymentAmount: Number,
        val payerId: String,
        val payerName: String
)