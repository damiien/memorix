package io.memorix.user.data

import io.memorix.user.User
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(val id: String, val email: String, val name: String, val password: String) {

    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(user.id.toString(), user.email, user.name, user.password)
        }
    }

}
