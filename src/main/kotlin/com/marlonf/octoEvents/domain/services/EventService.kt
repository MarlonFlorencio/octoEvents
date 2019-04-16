package com.marlonf.octoEvents.domain.services

import com.marlonf.octoEvents.domain.Event
import com.marlonf.octoEvents.domain.repository.EventRepository

interface EventService {
    fun listEventsByIssueNumber(number: Long?): List<Event>
    fun create(event: Event): Boolean
}

class EventServiceImpl(private val eventRepository: EventRepository) : EventService {

    override fun listEventsByIssueNumber(number: Long?): List<Event> {
        if (number == null) return emptyList()
        return eventRepository.listEventsByIssueNumber(number) ?: emptyList()
    }

    override fun create(event: Event): Boolean {
        return eventRepository.create(event)
    }
}