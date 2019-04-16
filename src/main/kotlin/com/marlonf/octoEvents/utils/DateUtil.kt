package com.marlonf.octoEvents.utils

import org.joda.time.DateTime

object DateUtil {

    fun parseToDateTime(value: String?): DateTime? {
        if (value == null || value.isEmpty()) {
            return null
        }

        try {
            return DateTime.parse(value)
        } catch (e: Exception) {
            throw IllegalArgumentException("Invalid data [$value]")
        }
    }

}