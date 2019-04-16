package com.marlonf.octoEvents.domain

import org.joda.time.DateTime
import java.util.*

data class Event(
    val id: UUID? = null,
    val action: String,
    val number: Long,
    val title: String,
    val createdAt: DateTime? = null,
    val updatedAt: DateTime? = null,
    val closedAt: DateTime? = null,
    val body: String
)