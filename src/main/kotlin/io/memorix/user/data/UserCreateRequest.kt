package io.memorix.user.data

import io.memorix.core.data.DataObject
import io.memorix.user.User
import kotlinx.serialization.Serializable
import java.util.*
import io.memorix.core.util.hash
import io.konform.validation.*
import io.konform.validation.jsonschema.pattern

@Serializable
data class UserCreateRequest(val email: String, val name: String, val password: String) :
    DataObject<UserCreateRequest, User>() {

    override fun validate() {
        val validation = Validation<UserCreateRequest> {
            UserCreateRequest::email {
                pattern(".+@.+\\..+")
            }
            UserCreateRequest::name {
                pattern(DataObject.Companion.NOT_BLANK_REGEX)
            }
            UserCreateRequest::password {
                pattern(DataObject.Companion.NOT_BLANK_REGEX)
            }
        }
        val result = validation.validate(this)
        if (result is Invalid) {
            throw IllegalStateException(result.errors.toString())
        }
    }

    override fun toEntity(): User {
        return User(
            id = UUID.randomUUID(),
            email = this.email,
            name = this.name,
            password = hash(this.password, "SHA-256")
        )
    }

}
