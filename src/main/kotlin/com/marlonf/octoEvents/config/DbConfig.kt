package com.marlonf.octoEvents.config

import org.jetbrains.exposed.sql.Database

fun DbConfig(jdbcUrl: String, username: String, password: String, driver: String) {

        Database.connect(
                url = jdbcUrl,
                driver = driver,
                user = username,
                password = password
        )
}