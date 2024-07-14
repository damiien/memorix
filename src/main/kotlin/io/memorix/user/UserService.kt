package io.memorix.user

import io.memorix.core.service.Service
import io.memorix.core.util.hash
import io.memorix.user.data.UserCreateRequest
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UserService(override var repository: UserRepository) : Service<UserRepository, User, UUID>(repository) {

    fun findByName(query: String?, limit: Int): List<User> {
        return transaction {
            repository.findByName(query, limit).toList();
        }
    }

    fun create(user: UserCreateRequest): User {
        return transaction {
            User.new {
                email = user.email
                name = user.name
                password = hash(user.password, "SHA-256")
            }
        }
    }

}
