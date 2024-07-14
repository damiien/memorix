package io.memorix.user

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column

object UserTable : UUIDTable("users") {

    val email: Column<String> = varchar("email", 255).uniqueIndex()
    val name: Column<String> = varchar("name", 255)
    val password: Column<String> = varchar("password", 128)

}
