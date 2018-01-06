package com.erwindev.openpayment.util

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Created by erwinalberto on 1/5/18.
 */

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="erwindev")
class ApplicationSettings {

    lateinit var apiVersion: String
    lateinit var paymentFileName: String
}