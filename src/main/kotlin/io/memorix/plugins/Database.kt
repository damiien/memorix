package io.memorix.plugins

import io.ktor.server.application.*
import io.ktor.server.config.*
import io.memorix.user.UserTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.inject

fun Application.configureDatabases() {
    val database: Database by inject()
    transaction(database) {
        SchemaUtils.create(UserTable)
    }
}
