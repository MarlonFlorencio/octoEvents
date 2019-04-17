package com.marlonf.octoEvents.config

import io.javalin.Javalin
import com.marlonf.octoEvents.controller.EventController
import io.javalin.JavalinEvent
import org.koin.core.KoinProperties

import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.getProperty
import org.koin.standalone.inject

class AppConfig() : KoinComponent {

    private val eventController by inject<EventController>()

    fun setup() :Javalin {

        startKoin(AppModules.modules(),
                KoinProperties(true, true))

        return Javalin.create().apply {

            exception(Exception::class.java) { e, _ -> e.printStackTrace() }

            error(404) { ctx -> ctx.json("not found") }

            disableStartupBanner()

            routes { eventController.getRouters() }

            requestLogger { ctx, timeMs ->
                println("${ctx.method()} ${ctx.status()} ${ctx.path()} took $timeMs ms")
            }

            event(JavalinEvent.SERVER_STOPPED) { stopKoin() }

            port(getProperty("server.port"))

        }
    }
}
