package io.memorix.user.data

import io.memorix.user.User
import kotlinx.serialization.Serializable

@Serializable
data class UserSearchResponse(val users: List<UserResponse> = listOf(), val total:Int = 0) {

    companion object {
        fun from(users: List<User>): UserSearchResponse {
            val data = users.map {
                UserResponse.from(it)
            }
            return UserSearchResponse(data, data.size);
        }
    }

}
