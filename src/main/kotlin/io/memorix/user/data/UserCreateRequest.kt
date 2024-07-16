package io.memorix.user.data

import io.memorix.user.User
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserCreateRequest(val email: String, val name: String, val password: String) {

    fun toUser(): User {
        return User(
            id = UUID.randomUUID(),
            email = this.email,
            name = this.name,
            password = this.password
        )
    }

}
