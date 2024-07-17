package io.memorix

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.memorix.plugins.*
import io.memorix.user.userModule
import org.koin.core.Koin
import org.koin.core.context.startKoin

fun main() {
    val config = ApplicationConfig(null)
    embeddedServer(CIO, port = config.port, host = config.host, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    startKoin()
    configureDatabases()
    configureHTTP()
    configureSerialization()
    configureRouting()
}

fun startKoin(): Koin = startKoin {
    modules(
        applicationModule,
        userModule
    )
}.koin
