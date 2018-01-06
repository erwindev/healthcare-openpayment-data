package com.erwindev.openpayment

import com.erwindev.openpayment.domain.OpenPayment
import com.erwindev.openpayment.service.OpenPaymentService
import com.erwindev.openpayment.util.ApplicationSettings
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.io.FileReader
import com.opencsv.enums.CSVReaderNullFieldIndicator
import com.opencsv.CSVReaderBuilder

/**
 * Created by erwinalberto on 1/5/18.
 */
@SpringBootApplication
class Application: CommandLineRunner{

    @Autowired
    lateinit var applicationSettings: ApplicationSettings

    @Autowired
    lateinit var openPaymentService: OpenPaymentService

    override fun run(args: Array<String>) {
        val csvReader = CSVReaderBuilder(FileReader(applicationSettings.paymentFileName))
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                // Skip the header
                .withSkipLines(1)
                .build()


        csvReader.forEach{ line ->
            var openPayment: OpenPayment = OpenPayment(providerId = line[5],
                    providerName = line[6] + " " + line[7] + " " + line[8],
                    paymentAmount = line[30].toDouble(),
                    payerId = line[26],
                    payerName = line[27])

            openPaymentService.addOpenPayment(openPayment)
        }
    }
}


fun main(args: Array<String>) {
     SpringApplication.run(Application::class.java, *args)
}