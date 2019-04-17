package com.marlonf.octoEvents.controller

import com.marlonf.octoEvents.config.AppConfig
import io.javalin.Javalin
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class IssueEventServiceTest {

    private lateinit var app: Javalin
    private var baseUrl: String = ""

    @Before
    fun setUp() {
        app = AppConfig().setup().start()
        baseUrl = "http://localhost:${app.port()}/issues"
    }

    @After
    fun tearDown() {
        app.stop()
    }

    @Test
    fun `Asserts endpoint found`() {
        val response = khttp.get(url = "$baseUrl/1/events")
        assertEquals(200, response.statusCode)
        assertEquals(0, response.jsonArray.length())
    }

    @Test
    fun `Asserts endpoint found3`() {
        val response = khttp.get(url = "$baseUrl/xpto/events")
        assertEquals(400, response.statusCode)
    }

    @Test
    fun `Asserts post endpoint returns 400`() {
        val response = khttp.post(url = "$baseUrl", data = "Invalid")
        assertEquals(400, response.statusCode)
    }

    @Test
    fun `Asserts post endpoint returns 200`() {

        val number = 5

        val validPayload =
        """ {
            "action": "opened",
            "issue": {
                "title": "Test",
                "number": $number,
                "created_at": "2019-01-11T01:10:56Z",
                "updated_at": "2019-02-12T18:10:56Z",
                "closed_at":  "2019-03-13T18:10:56Z",
                "body": "Test Test"
            }
        } """

        val responsePost = khttp.post(url = "$baseUrl", data = validPayload)
        assertEquals(201, responsePost.statusCode)

        val responseGet = khttp.get(url = "$baseUrl/$number/events")
        assertEquals(200, responseGet.statusCode)
        assertEquals(1, responseGet.jsonArray.length())
    }

}