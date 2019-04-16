package com.marlonf.octoEvents.dto

import junit.framework.Assert.*
import org.joda.time.format.DateTimeFormat
import org.junit.Test

class EventDtoParserTest {

    private val format = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

    @Test
    fun `Event values should be equal to EventDto`() {

        val dto = EventDto(action = "ACTION",
                            issue = IssueDto(title = "TITLE",
                                            number = 1000,
                                        created_at = "2019-04-13T16:10:56Z",
                                        updated_at = "2019-05-13T17:10:56Z",
                                         closed_at = "2019-06-13T18:10:56Z",
                                              body = "BODY")
        )

        val event = EventDtoParser.parse(dto)

        assertEquals(dto.action, event.action)
        assertEquals(dto.issue.title, event.title)
        assertEquals(dto.issue.number, event.number)
        assertEquals(dto.issue.body, event.body)
        assertEquals(dto.issue.created_at, event.createdAt!!.toString(format))
        assertEquals(dto.issue.updated_at, event.updatedAt!!.toString(format))
        assertEquals(dto.issue.closed_at, event.closedAt!!.toString(format))

        assertNotNull(event.action)
        assertNotNull(dto.issue.title, event.title)
        assertNotNull(event.number)
        assertNotNull(event.body)
        assertNotNull(event.createdAt)
        assertNotNull(event.updatedAt)
        assertNotNull(event.closedAt)
    }


    @Test(expected = IllegalArgumentException::class)
    fun `Invalid Date at created_at should throw a Exception `() {
        val dto = EventDto(action = "ACTION",
                issue = IssueDto(title = "TITLE",
                        number = 1000,
                        created_at = "xpto",
                        updated_at = "2019-05-13T17:10:56Z",
                        closed_at = "2019-06-13T18:10:56Z",
                        body = "BODY")
        )

        EventDtoParser.parse(dto)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Invalid Date at updated_at should throw a Exception `() {
        val dto = EventDto(action = "ACTION",
                issue = IssueDto(title = "TITLE",
                        number = 1000,
                        created_at = "2019-04-13T16:10:56Z",
                        updated_at = "xyz",
                        closed_at = "2019-06-13T18:10:56Z",
                        body = "BODY")
        )

        EventDtoParser.parse(dto)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Invalid Date at closed_at should throw a Exception `() {
        val dto = EventDto(action = "ACTION",
                issue = IssueDto(title = "TITLE",
                        number = 1000,
                        created_at = "2019-04-13T16:10:56Z",
                        updated_at = "2019-05-13T17:10:56Z",
                        closed_at = "qwe",
                        body = "BODY")
        )

        EventDtoParser.parse(dto)
    }

}
