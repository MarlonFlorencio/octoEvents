package com.marlonf.octoEvents.controller

import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.*
import com.marlonf.octoEvents.domain.services.EventService
import com.marlonf.octoEvents.dto.EventDto
import com.marlonf.octoEvents.dto.EventDtoParser
import org.koin.standalone.KoinComponent


class EventController(private val eventService: EventService) : KoinComponent {

    fun getRouters() {
        path("/issues") {
            post(this::createEvent)
            get(":number/events", this::listEventsByIssueNumber)
        }
    }

    fun createEvent(ctx: Context) {

        try {
            val eventDto = ctx.bodyAsClass(EventDto::class.java)
            eventService.create(EventDtoParser.parse(eventDto))
            ctx.status(201)

        } catch (e: IllegalArgumentException) {
            ctx.status(400).json(e.message!!)

        } catch (e: Exception) {
            ctx.status(500)
        }
    }

    fun listEventsByIssueNumber(ctx: Context) {

        try {
            val number = ctx.pathParam("number").toLongOrNull()
                    ?: throw IllegalArgumentException("Invalid parameter")

            ctx.json(eventService.listEventsByIssueNumber(number))

        } catch (e: IllegalArgumentException) {
            ctx.status(400).json(e.message!!)

        } catch (e: Exception) {
            ctx.status(500)
        }
    }

}