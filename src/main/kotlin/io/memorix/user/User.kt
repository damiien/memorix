package io.memorix.user

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class User(
    id: EntityID<UUID>,
) : UUIDEntity(id) {

    companion object : UUIDEntityClass<User>(UserTable)

    var email by UserTable.email
    var name by UserTable.name
    var password by UserTable.password

}
