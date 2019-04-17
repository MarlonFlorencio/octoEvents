package com.marlonf.octoEvents.dto

import com.marlonf.octoEvents.domain.Event

object EventParser {

    fun parse(dto: EventDto): Event {

        return Event(
                action = dto.action,
                number = dto.issue.number,
                title = dto.issue.title,
                createdAt = dto.issue.created_at,
                updatedAt = dto.issue.updated_at,
                closedAt = dto.issue.closed_at,
                body = dto.issue.body)
    }
}
