package com.marlonf.octoEvents.domain.repository

import com.marlonf.octoEvents.domain.model.Event
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.DataSource

internal object Events : LongIdTable() {

    val action: Column<String> = varchar("action", 200)
    val number: Column<Long> = long("number")
    val title: Column<String> = varchar("title", 150)
    val created_at: Column<String?> = varchar("created_at", 30).nullable()
    val updated_at: Column<String?> = varchar("updated_at", 30).nullable()
    val closed_at: Column<String?> = varchar("closed_at", 30).nullable()
    val body: Column<String> = varchar("body", 1000)

    fun toDomain(row: ResultRow): Event {
        return Event(
                id = row[Events.id].value,
                action = row[Events.action],
                number = row[Events.number],
                title = row[Events.title],
                created_at = row[Events.created_at],
                updated_at = row[Events.updated_at],
                closed_at = row[Events.closed_at],
                body = row[Events.body]
        )
    }
}

class EventRepository(private val dataSource: DataSource) {

    init {
        transaction(Database.connect(dataSource)) {
            SchemaUtils.create(Events)
        }
    }

    fun listEventsByIssueNumber(number: Long): List<Event>? {
        return transaction(Database.connect(dataSource)) {
            Events.select { Events.number eq number }
                    .map { Events.toDomain(it) }
                    .toList()
        }
    }

    fun create(event: Event): Long? {
        println(event)
        return transaction(Database.connect(dataSource)) {
            Events.insertAndGetId { row ->
                row[Events.action] = event.action
                row[Events.number] = event.number
                row[Events.title] = event.title
                row[Events.created_at] = event.created_at
                row[Events.updated_at] = event.updated_at
                row[Events.closed_at] = event.closed_at
                row[Events.body] = event.body
            }.value
        }
    }
}