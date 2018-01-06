package com.erwindev.openpayment.dao

import com.erwindev.openpayment.domain.OpenPayment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.sql.SQLException



/**
 * Created by erwinalberto on 1/6/18.
 */
@Repository
class OpenPaymentDao {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    fun insert(openPayment: OpenPayment){
        SimpleJdbcInsert(jdbcTemplate)
                .withTableName("open_payment")
                .apply {
                    setGeneratedKeyName("id")
                    execute(
                            mapOf(
                                    "provider_id" to openPayment.providerId,
                                    "provider_name" to openPayment.providerName,
                                    "payment_amount" to openPayment.paymentAmount,
                                    "payer_id" to openPayment.payerId,
                                    "payer_name" to openPayment.payerName
                            )
                    )
                }
    }

    fun findAll(): List<OpenPayment> = jdbcTemplate.query(
            """SELECT id,
                      provider_id,
                      provider_name,
                      payment_amount,
                      payer_id,
                      payer_name
                FROM open_payment""",
            {
                rs: ResultSet, _ : Int ->
                    OpenPayment(
                            rs.getLong("id"),
                            rs.getString("provider_id"),
                            rs.getString("provider_name"),
                            rs.getBigDecimal("payment_amount"),
                            rs.getString("payer_id"),
                            rs.getString("payer_name"))
            })

    fun findByProviderId(providerId: String): List<OpenPayment> = jdbcTemplate.query(
            """SELECT id,
                     provider_id,
                     provider_name,
                     payment_amount,
                     payer_id,
                     payer_name
              FROM open_payment
              WHERE provider_id = ?""", arrayOf(providerId))
        {
            rs: ResultSet, _ : Int ->
                OpenPayment(
                        rs.getLong("id"),
                        rs.getString("provider_id"),
                        rs.getString("provider_name"),
                        rs.getBigDecimal("payment_amount"),
                        rs.getString("payer_id"),
                        rs.getString("payer_name"))
        }

    fun findByPayerId(payerId: String): List<OpenPayment> = jdbcTemplate.query(
            """SELECT id,
                     provider_id,
                     provider_name,
                     payment_amount,
                     payer_id,
                     payer_name
              FROM open_payment
              WHERE payer_id = ?""", arrayOf(payerId))
    {
        rs: ResultSet, _ : Int ->
        OpenPayment(
                rs.getLong("id"),
                rs.getString("provider_id"),
                rs.getString("provider_name"),
                rs.getBigDecimal("payment_amount"),
                rs.getString("payer_id"),
                rs.getString("payer_name"))
    }

    fun findById(id: Number): OpenPayment{

        var openPayment : OpenPayment = jdbcTemplate.queryForObject(
                """SELECT id,
                     provider_id,
                     provider_name,
                     payment_amount,
                     payer_id,
                     payer_name
              FROM open_payment
              WHERE id = ?""", arrayOf(id), OpenPaymentMapper())

        return openPayment
    }

}

private class OpenPaymentMapper : RowMapper<OpenPayment> {
    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, rowNum: Int): OpenPayment {
        val openPayment = OpenPayment(
                rs.getLong("id"),
                rs.getString("provider_id"),
                rs.getString("provider_name"),
                rs.getBigDecimal("payment_amount"),
                rs.getString("payer_id"),
                rs.getString("payer_name"))
        return openPayment
    }
}