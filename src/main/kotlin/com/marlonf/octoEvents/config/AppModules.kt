package com.marlonf.octoEvents.config

import com.marlonf.octoEvents.controller.EventController
import com.marlonf.octoEvents.domain.repository.EventRepository
import com.marlonf.octoEvents.domain.services.EventService
import com.marlonf.octoEvents.domain.services.EventServiceImpl
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


object AppModules {

    fun modules() : Module {
        return module {
            single { AppConfig() }
            single { EventController(get()) }
            single { EventServiceImpl(get()) as EventService }
            single { EventRepository(get()) }
            single { DbConfig(  getProperty("db.jdbc.url"),
                                getProperty("db.username"),
                                getProperty("db.password"),
                                getProperty("db.driver")
                    ).getDataSource() }
        }
    }
}