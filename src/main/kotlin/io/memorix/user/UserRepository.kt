package io.memorix.user

import io.memorix.core.repository.Repository
import io.memorix.core.repository.ilike
import io.memorix.core.util.hash
import org.jetbrains.exposed.sql.*
import java.util.*

class UserRepository : Repository<User, UUID>() {

    private fun ResultRow.toUser() = User(
        id = this[UserTable.id].value,
        email = this[UserTable.email],
        name = this[UserTable.name],
        password = this[UserTable.password]
    )

    override fun findAll(limit: Int): List<User> {
        return UserTable.selectAll().limit(limit).map { it.toUser() }
    }

    override fun findById(id: UUID): User? {
        return UserTable.select { UserTable.id eq id }.map { it.toUser() }.firstOrNull()
    }

    override fun create(entity: User): User? {
        val id = UserTable.insertAndGetId {
            it[email] = entity.email
            it[name] = entity.name
            it[password] = hash(entity.password, "SHA-256")
        }
        return findById(id.value)
    }

    fun findByName(name: String?, limit: Int): List<User> {
        if (name != null)
            return UserTable.select { (UserTable.name ilike name) }.limit(limit).map { it.toUser() }
        else
            return UserTable.selectAll().limit(limit).map { it.toUser() };
    }

}
