package com.marlonf.octoEvents.domain

import java.util.*

data class Event(
    val id: UUID? = null,
    val action: String,
    val number: Long,
    val title: String,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val closedAt: String? = null,
    val body: String
)