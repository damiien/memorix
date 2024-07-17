package io.memorix

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.config.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import io.memorix.plugins.*
import io.memorix.user.User
import io.memorix.user.data.UserCreateRequest
import io.memorix.user.data.UserResponse
import io.memorix.user.data.UserSearchResponse
import org.junit.Assert
import java.util.*
import kotlin.random.Random
import kotlin.test.*

class ApplicationTest {

    @Test
    fun testUsers() = testApplication {
        application {
            startKoin()
            configureDatabases()
            configureHTTP()
            configureSerialization()
            configureRouting()
        }
        environment {
            config = ApplicationConfig(null)
        }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        // test user create
        val email = generateEmail()
        var user: UserResponse = client.post("/users") {
            contentType(ContentType.Application.Json)
            setBody(UserCreateRequest(email, "Test", "Test"))
        }.body()
        Assert.assertNotNull("ID cannot be null", user.id)
        Assert.assertEquals(email, user.email)

        // test find user by id
        user = client.get("/users/${user.id}") {
            contentType(ContentType.Application.Json)
        }.body()
        Assert.assertNotNull("User response cannot be null", user)
        Assert.assertEquals(email, user.email)

        // test user collection search
        val users: UserSearchResponse = client.get("/users") {
            contentType(ContentType.Application.Json)
        }.body()
        Assert.assertNotNull("User search response cannot be null", users)
        Assert.assertTrue("Users collection cannot be empty", users.users.isNotEmpty())
        Assert.assertTrue("Users collection total count must be > 0", users.total > 0)
    }

    fun generateEmail(): String {
        val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val id: String = (1..10)
            .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
            .joinToString("")
        return "$id@test.com"
    }
}
