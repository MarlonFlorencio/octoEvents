package com.marlonf.octoEvents.domain.repository

import com.marlonf.octoEvents.domain.Event
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*


private object EventTable : UUIDTable("events") {

    val action = varchar("action", 200)
    val number = long("number")
    val title = varchar("title", 150)
    val createdAt = varchar("created_at",50).nullable()
    val updatedAt = varchar("updated_at",50).nullable()
    val closedAt = varchar("closed_at", 50).nullable()
    val body: Column<String> = varchar("body", 1000)

    fun toDomain(entity: EventEntity): Event {
        return Event(
                id = entity.id.value,
                action = entity.action,
                number = entity.number,
                title = entity.title,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt,
                closedAt = entity.closedAt,
                body = entity.body
        )
    }
}

class EventEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<EventEntity>(EventTable)
    var action by EventTable.action
    var number by EventTable.number
    var title by EventTable.title
    var createdAt by EventTable.createdAt
    var updatedAt by EventTable.updatedAt
    var closedAt by EventTable.closedAt
    var body by EventTable.body
}

class EventRepository {

    init {
        transaction {
            SchemaUtils.create(EventTable)
        }
    }

    fun create(event: Event): Boolean  = transaction  {
        EventEntity.new {
            this.action = event.action
            this.number = event.number
            this.title = event.title
            this.createdAt = event.createdAt
            this.updatedAt = event.updatedAt
            this.closedAt = event.closedAt
            this.body = event.body
        }.flush()
    }

    fun listEventsByIssueNumber(number: Long): List<Event>? = transaction {
        EventEntity.find { EventTable.number eq number }
                .orderBy(EventTable.updatedAt to SortOrder.ASC)
                   .map { ent -> EventTable.toDomain(ent) }
    }
}