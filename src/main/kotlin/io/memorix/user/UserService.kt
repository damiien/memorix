package io.memorix.user

import io.memorix.core.service.Service
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UserService(override var repository: UserRepository) : Service<UserRepository, User, UUID>(repository) {

    fun findByName(query: String?, limit: Int): List<User> {
        return transaction {
            repository.findByName(query, limit);
        }
    }

}
