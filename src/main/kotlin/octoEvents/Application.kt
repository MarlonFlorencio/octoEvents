package octoEvents

import io.javalin.Javalin
import octoEvents.controller.EventController
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

class Application : KoinComponent {

    fun start() {

        startKoin { modules(applicationModule) }

        val eventController by inject<EventController>()

        Javalin.create().apply {

            exception(Exception::class.java) { e, _ -> e.printStackTrace() }

            error(404) { ctx -> ctx.json("not found") }

            //ROUTERS
            routes { eventController.getRouters() }

        }.start(7000)
    }
}
