package com.marlonf.octoEvents.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
data class EventDto(
        val action: String,
        val issue: IssueDto
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class IssueDto(
        val title: String,
        val number: Long,
        val created_at: String?,
        val updated_at: String?,
        val closed_at: String?,
        val body: String
)