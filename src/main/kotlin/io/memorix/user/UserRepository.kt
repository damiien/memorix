package io.memorix.user

import io.memorix.core.repository.Repository
import io.memorix.core.repository.ilike
import org.jetbrains.exposed.sql.*
import java.util.*

class UserRepository : Repository<User, UUID>() {

    override fun findAll(limit: Int): SizedIterable<User> {
        return User.all().limit(limit)
    }

    override fun findById(id: UUID): User? {
        return User.findById(id)
    }

    fun findByName(name: String?, limit: Int): SizedIterable<User> {
        if (name != null)
            return User.find { (UserTable.name ilike name) }.limit(limit)
        else
            return User.all().limit(limit);
    }

}
