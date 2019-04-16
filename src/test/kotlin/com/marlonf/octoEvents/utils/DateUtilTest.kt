package com.marlonf.octoEvents.utils

import junit.framework.Assert.*
import org.junit.Test

class DateUtilTest {

    @Test
    fun `Return should be null`() {
        assertNull(DateUtil.parseToDateTime(null))
    }

    @Test
    fun `Empty String should return null `() {
        assertNull(DateUtil.parseToDateTime(" "))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Invalid Date should throw a Exception `() {
        assertNull(DateUtil.parseToDateTime("xpto"))
    }

    @Test
    fun `Valid String should return DateTime `() {
        val actual = "2019-04-13T16:10:56.000Z"
        val dateTime = DateUtil.parseToDateTime(actual)
        assertNotNull(dateTime)
        assertEquals(actual, dateTime.toString())
    }

}

