package io.memorix.core.service

import io.memorix.core.repository.IRepository
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.sql.transactions.transaction

abstract class Service<R : IRepository<E, ID>, E : Entity<ID>, ID : Comparable<ID>>(override val repository: R) :
    IService<R, E, ID> {

    override fun findById(id: ID): E? {
        return transaction {
            repository.findById(id)
        }
    }

}
