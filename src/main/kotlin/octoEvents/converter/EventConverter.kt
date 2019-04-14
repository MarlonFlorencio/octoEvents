package octoEvents.converter

import com.beust.klaxon.Klaxon
import octoEvents.model.Event
import java.lang.IllegalArgumentException

fun createEventFromPayLoad(body: String): Event {

    val eventDTO = Klaxon().parse<EventDTO>(body)
            ?: throw IllegalArgumentException("BAD REQUEST")

    val event = Event()
    event.action = eventDTO.action
    event.number = eventDTO.issue.number
    event.title = eventDTO.issue.title
    event.state = eventDTO.issue.state
    event.created_at = eventDTO.issue.created_at
    event.updated_at = eventDTO.issue.updated_at
    event.closed_at = eventDTO.issue.closed_at
    event.body = eventDTO.issue.body

    return event
}

private data class EventDTO(
        val action: String,
        val issue: IssueDTO
)

private data class IssueDTO(
        val title: String,
        val number: Long,
        val state: String,
        val created_at: String?,
        val updated_at: String?,
        val closed_at: String?,
        val body: String
)