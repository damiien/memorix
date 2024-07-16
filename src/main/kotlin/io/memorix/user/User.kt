package io.memorix.user

import io.memorix.core.entity.Entity
import io.memorix.core.serialize.UuidSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class User(
    @Serializable(with = UuidSerializer::class)
    override var id: UUID,
    var email: String,
    var name: String,
    var password: String
) : Entity<User, UUID>() {

}
