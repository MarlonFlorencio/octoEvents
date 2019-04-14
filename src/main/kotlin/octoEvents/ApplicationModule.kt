package octoEvents

import octoEvents.controller.EventController
import octoEvents.services.EventService
import octoEvents.services.EventServiceImpl
import org.koin.dsl.module

val applicationModule = module {

    single { EventServiceImpl() as EventService }
    single { EventController(get()) }

}