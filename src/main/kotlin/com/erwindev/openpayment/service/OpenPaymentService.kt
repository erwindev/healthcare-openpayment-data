package com.erwindev.openpayment.service

import com.erwindev.openpayment.dao.OpenPaymentDao
import com.erwindev.openpayment.domain.OpenPayment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by erwinalberto on 1/6/18.
 */
@Service
class OpenPaymentService {
    @Autowired
    lateinit var openPaymentDao: OpenPaymentDao

    fun addOpenPayment(openPayment: OpenPayment){
        openPaymentDao.insert(openPayment)
    }

    fun findAllOpenPayments(): List<OpenPayment> = openPaymentDao.findAll()

    fun findProviderOpenPayments(providerId: String): List<OpenPayment> = openPaymentDao.findByProviderId(providerId)

    fun findPayerOpenPayments(paymentId: String): List<OpenPayment> = openPaymentDao.findByPayerId(paymentId)

    fun findOpenPayment(id: Number): OpenPayment = openPaymentDao.findById(id)
}
