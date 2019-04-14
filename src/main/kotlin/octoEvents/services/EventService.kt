package octoEvents.services

import octoEvents.model.Event

interface EventService {
    fun getAllByNumber(number: Long?): List<Event>
    fun create(event: Event)
}

class EventServiceImpl : EventService {

    override fun getAllByNumber(number: Long?): List<Event> {
        if (number == null) return emptyList()
        return emptyList()
    }

    override fun create(event: Event) {
        println(event)
        println("ssssssss%%%%%%%%%%%%%5")
    }
}