package com.erwindev.openpayment.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Created by erwinalberto on 1/5/18.
 */
@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Value("\${info.app.name}") lateinit var appName: String
    @Value("\${info.app.description}") lateinit var appDescription: String
    @Value("\${info.app.contact}") lateinit var appContact: String
    @Value("\${info.app.email}") lateinit var appEmail: String

    @Bean
    open fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.erwindev.openpayment.controller"))
                .paths(regex("/api/openpayment/v1.*"))
                .build().apiInfo(apiInfo())

    fun apiInfo(): ApiInfo {

        var contact: Contact = Contact(appContact, "", appEmail)

        return ApiInfoBuilder()
                .title(appName)
                .description(appDescription)
                .version("1.0")
                .termsOfServiceUrl("Terms of Service URL")
                .contact(contact)
                .license("License of API")
                .licenseUrl("API License URL")
                .build()

    }






}