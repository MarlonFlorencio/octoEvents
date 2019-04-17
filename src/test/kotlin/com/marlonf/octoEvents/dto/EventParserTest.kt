package com.marlonf.octoEvents.dto

import junit.framework.Assert.*
import org.junit.Test

class EventParserTest {

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

        val event = EventParser.parse(dto)

        assertEquals(dto.action, event.action)
        assertEquals(dto.issue.title, event.title)
        assertEquals(dto.issue.number, event.number)
        assertEquals(dto.issue.body, event.body)
        assertEquals(dto.issue.created_at, event.createdAt)
        assertEquals(dto.issue.updated_at, event.updatedAt)
        assertEquals(dto.issue.closed_at, event.closedAt)

        assertNotNull(event.action)
        assertNotNull(dto.issue.title, event.title)
        assertNotNull(event.number)
        assertNotNull(event.body)
        assertNotNull(event.createdAt)
        assertNotNull(event.updatedAt)
        assertNotNull(event.closedAt)
    }

}
