package io.memorix

import io.ktor.server.application.*
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module

val applicationModule = module {

    single<Database> {
        val config = ApplicationConfig(null)
        val database = Database.connect(
            url = "jdbc:postgresql://" + config.property("database.host").getString() + ":" +
                    config.property("database.port").getString() + "/" +
                    config.property("database.name").getString(),
            user = config.property("database.user").getString(),
            password = config.property("database.password").getString()
        )
        database
    }

}
