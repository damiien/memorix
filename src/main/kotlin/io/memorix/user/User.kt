package io.memorix.user

import io.memorix.core.entity.Entity
import java.util.*

data class User(
    override var id: UUID,
    var email: String,
    var name: String,
    var password: String
) : Entity<User, UUID>() {}
