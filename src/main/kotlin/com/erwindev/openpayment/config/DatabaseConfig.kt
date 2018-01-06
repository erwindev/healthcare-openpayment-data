package com.erwindev.openpayment.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import javax.sql.DataSource


/**
 * Created by erwinalberto on 1/6/18.
 */
@Configuration
class DatabaseConfig {

    @Value("\${db.schema_file}") lateinit var schemaSql: String

    @Bean(name = arrayOf("dataSource"))
    fun dataSource(): DataSource {
        //This will create a new embedded database and run the schema.sql script
        return EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(schemaSql)
                .build()
    }

    @Bean
    fun jdbcTemplate(@Qualifier("dataSource") dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }
}