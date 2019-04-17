package com.marlonf.octoEvents.domain.service

import com.marlonf.octoEvents.config.AppModules
import com.marlonf.octoEvents.domain.Event
import com.marlonf.octoEvents.domain.services.EventService
import junit.framework.Assert.*
import org.joda.time.DateTime
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.KoinProperties
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest

class EventServiceTest : KoinTest {

    private val eventService by inject<EventService>()

    @Before
    fun setUp() {
        StandAloneContext.startKoin(AppModules.modules(),
                KoinProperties(true, true))
    }

    @After
    fun tearDown() {
        StandAloneContext.stopKoin()
    }

    @Test
    fun `Event list should be empty`() {
        val events = eventService.listEventsByIssueNumber(187)
        assertTrue(events.isEmpty())
    }

    @Test
    fun `Null number should return an empty list`() {
        val events = eventService.listEventsByIssueNumber(null)
        assertTrue(events.isEmpty())
    }

    @Test

    fun `Event should be created and be equal`() {
        val event = Event(action = "Action",
                body = "Body",
                number = 54,
                createdAt = "2019-04-13T16:10:56Z",
                updatedAt = "2019-05-13T17:10:56Z",
                closedAt = "2019-06-13T18:10:56Z",
                title = "Title")
        
        val created = eventService.create(event)
        assertTrue(created)

        val events = eventService.listEventsByIssueNumber(54)
        assertEquals(1, events.size)
        
        val eventFromList = events[0]

        assertEquals(event.action, eventFromList.action)
        assertEquals(event.title, eventFromList.title)
        assertEquals(event.number, eventFromList.number)
        assertEquals(event.body, eventFromList.body)
        assertEquals(event.createdAt, eventFromList.createdAt)
        assertEquals(event.updatedAt, eventFromList.updatedAt)
        assertEquals(event.closedAt, eventFromList.closedAt)

        assertNotNull(eventFromList.id)
        assertNotNull(eventFromList.action)
        assertNotNull(eventFromList.title)
        assertNotNull(eventFromList.number)
        assertNotNull(eventFromList.body)
        assertNotNull(eventFromList.createdAt)
        assertNotNull(eventFromList.updatedAt)
        assertNotNull(eventFromList.closedAt)
    }

    @Test
    fun `Event list should return two elements`() {

        val event = Event(action = "Action", body = "Body", number = 34, title = "Title")

        eventService.create(event)
        eventService.create(event)

        val events = eventService.listEventsByIssueNumber(34)
        assertEquals(2, events.size)
    }

}