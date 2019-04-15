package com.marlonf.octoEvents.domain.service

import com.marlonf.octoEvents.config.AppModules
import com.marlonf.octoEvents.domain.model.Event
import com.marlonf.octoEvents.domain.services.EventService
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.KoinProperties
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest

class IssueEventServiceTest : KoinTest {

    private val eventService by inject<EventService>()

    @Before
    fun setUp() {
        StandAloneContext.startKoin(listOf(AppModules.modules()),
                KoinProperties(true, true))
    }

    @After
    fun tearDown() {
        StandAloneContext.stopKoin()
    }

    @Test
    fun `Event list should be empty`() {
        val events = eventService.listEventsByIssueNumber(null)
        assertTrue(events.isEmpty())
    }

    @Test
    fun `Events should be created`() {
        val eventId = eventService.create(
                Event(action = "Test",
                        body = "Test",
                        number = 10,
                        title = "Test"))

        assertNotNull(eventId)

    }

}