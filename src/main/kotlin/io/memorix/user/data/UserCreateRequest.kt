package io.memorix.user.data

import kotlinx.serialization.Serializable

@Serializable
data class UserCreateRequest(val email: String, val name: String, val password: String) {
}
