package io.memorix.user

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import io.memorix.core.data.ErrorResponse
import io.memorix.user.data.UserCreateRequest
import io.memorix.user.data.UserResponse
import io.memorix.user.data.UserSearchResponse
import org.koin.ktor.ext.inject
import java.util.*

fun Route.user() {

    val service: UserService by inject()

    route("/users") {

        get {
            val query = call.request.queryParameters.get("query")
            val limit: Int = call.request.queryParameters.get("limit")?.toIntOrNull() ?: Int.MAX_VALUE
            call.respond(HttpStatusCode.OK, UserSearchResponse.from(service.findByName(query, limit)));
        }

        get("/{id}") {
            val id = call.parameters["id"]
            val user = service.findById(UUID.fromString(id))
            if (user != null) {
                call.respond(HttpStatusCode.OK, UserResponse.from(user))
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        post {
            try {
                val request = call.receive<UserCreateRequest>()
                call.respond(HttpStatusCode.Accepted, UserResponse.from(service.create(request.toUser())!!))
            } catch (e: Exception) {
                e.printStackTrace();
                call.respond(HttpStatusCode.BadRequest, ErrorResponse.factory(e))
            }
        }
    }

}
