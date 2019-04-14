package octoEvents.controller

import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.*
import octoEvents.converter.createEventFromPayLoad
import octoEvents.services.EventService
import org.koin.core.KoinComponent


class EventController(private val eventService: EventService) : KoinComponent {

    fun getRouters() {
        path("/issues") {
            post(this::createEvent)
            get(":number/events", this::getEvents)
        }
    }

    fun createEvent(ctx: Context) {

        try {
            eventService.create(createEventFromPayLoad(ctx.body()))
            ctx.status(201)

        } catch (e: IllegalArgumentException) {
            ctx.status(400).json(e.message!!)

        } catch (e: Exception) {
            ctx.status(500)
        }
    }

    fun getEvents(ctx: Context) {

        try {
            val number = ctx.pathParam("number").toLongOrNull()
                    ?: throw IllegalArgumentException("Invalid parameter")

            ctx.json(eventService.getAllByNumber(number))

        } catch (e: IllegalArgumentException) {
            ctx.status(400).json(e.message!!)

        } catch (e: Exception) {
            ctx.status(500)
        }
    }

}