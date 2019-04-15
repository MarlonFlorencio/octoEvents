package com.marlonf.octoEvents.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource

class DbConfig(jdbcUrl: String, username: String, password: String, driver: String) {

    private val dataSource: DataSource

    init {
        dataSource = HikariConfig().let { config ->
            config.jdbcUrl = jdbcUrl
            config.username = username
            config.password = password
            config.driverClassName = driver
            HikariDataSource(config)
        }
    }

    fun getDataSource(): DataSource {
        return dataSource
    }
}