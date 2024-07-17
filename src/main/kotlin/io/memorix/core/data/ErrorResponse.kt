package io.memorix.core.data

import io.ktor.server.plugins.*
import io.ktor.util.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.exceptions.ExposedSQLException

@Serializable
data class ErrorResponse(val error: String) {

    companion object {
        fun factory(e: Exception): ErrorResponse {
            return when(e) {
                is ExposedSQLException -> {
                    when {
                        ("duplicate key" in e.message!!) -> ErrorResponse("Duplicate e-mail: " +
                                ErrorResponse.Companion.extractEmail(e.message!!))
                        else -> ErrorResponse("Database error has occurred")
                    }
                }
                is BadRequestException -> ErrorResponse("Invalid request parameters")
                is IllegalStateException -> ErrorResponse("Validation error : ${e.message}")
                else -> ErrorResponse("Error occurred")
            }
        }

        private fun extractEmail(message: String): String {
            val regex = "\\(email\\)=\\(([a-zA-Z0-9.\\-@]*)\\)".toRegex();
            val result = regex.find(message)
            if (result != null && result.groups.size > 1)
                return result.groups[1]!!.value
            else return "unknown"
        }
    }

}
