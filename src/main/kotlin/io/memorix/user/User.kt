package io.memorix.user

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class User(
    id: EntityID<UUID>,
) : UUIDEntity(id) {

    constructor(id: EntityID<UUID>, email: String, name: String, password: String) : this(id) {
        this.email = email
        this.name = name
        this.password = password
    }

    companion object : UUIDEntityClass<User>(UserTable)

    var email by UserTable.email
    var name by UserTable.name
    var password by UserTable.password

}
