package io.memorix.user

import org.koin.dsl.module
import org.koin.dsl.single

val userModule = module {

    val repository = UserRepository()

    single<UserRepository> {
        repository
    }

    single<UserService> {
        UserService(repository)
    }

}
