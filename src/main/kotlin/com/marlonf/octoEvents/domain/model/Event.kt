package com.marlonf.octoEvents.domain.model

data class Event(
    val id: Long? = null,
    val action: String,
    val number: Long,
    val title: String,
    val created_at: String? = null,
    val updated_at: String? = null,
    val closed_at: String? = null,
    val body: String
)