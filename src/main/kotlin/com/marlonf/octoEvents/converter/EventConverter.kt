package com.marlonf.octoEvents.converter

import com.beust.klaxon.Klaxon
import com.marlonf.octoEvents.domain.model.Event
import java.lang.IllegalArgumentException

fun createEventFromPayLoad(body: String): Event {

    val eventDTO = Klaxon().parse<EventDTO>(body)
            ?: throw IllegalArgumentException("BAD REQUEST")

    return Event(
        action = eventDTO.action,
        number = eventDTO.issue.number,
        title = eventDTO.issue.title,
        created_at = eventDTO.issue.created_at,
        updated_at = eventDTO.issue.updated_at,
        closed_at = eventDTO.issue.closed_at,
        body = eventDTO.issue.body
    )
}

private data class EventDTO(
        val action: String,
        val issue: IssueDTO
)

private data class IssueDTO(
        val title: String,
        val number: Long,
        val created_at: String?,
        val updated_at: String?,
        val closed_at: String?,
        val body: String
)