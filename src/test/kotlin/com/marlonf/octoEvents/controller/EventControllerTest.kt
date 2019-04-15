package com.marlonf.octoEvents.controller

import com.marlonf.octoEvents.config.AppConfig
import io.javalin.Javalin
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class IssueEventServiceTest () {

    private lateinit var app: Javalin
    private var baseUrl:String = ""

    @Before
    fun setUp() {
        app = AppConfig().setup().start()
        baseUrl = "http://localhost:${app.port()}"
    }

    @After
    fun tearDown() {
        app.stop()
    }

    @Test
    fun `Asserts endpoint found`() {
        val response = khttp.get( url = "$baseUrl/issues/1/events")
        assertEquals(200, response.statusCode)
    }

}