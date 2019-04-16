package com.marlonf.octoEvents.dto

import com.marlonf.octoEvents.domain.Event
import com.marlonf.octoEvents.utils.DateUtil

object EventDtoParser {

    fun parse(dto: EventDto): Event {

        return Event(
                action = dto.action,
                number = dto.issue.number,
                title = dto.issue.title,
                createdAt = DateUtil.parseToDateTime(dto.issue.created_at),
                updatedAt = DateUtil.parseToDateTime(dto.issue.updated_at),
                closedAt = DateUtil.parseToDateTime(dto.issue.closed_at),
                body = dto.issue.body)
    }
}
