package com.erwindev.openpayment.feature

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

/**
 * Created by erwinalberto on 3/5/18.
 */

@RunWith(Cucumber::class)
@CucumberOptions(
        features = ["src/test/resources/cucumber/features"],
        tags = ["not @ignored"])
class OpenPaymentCucumberTest