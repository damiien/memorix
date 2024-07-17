package io.memorix.core.service

import io.memorix.core.entity.IEntity
import io.memorix.core.repository.IRepository
import org.jetbrains.exposed.sql.transactions.transaction

abstract class Service<R : IRepository<E, ID>, E : IEntity<E, ID>, ID : Comparable<ID>>(override val repository: R) :
    IService<R, E, ID> {

    override fun findById(id: ID): E? {
        return transaction {
            repository.findById(id)
        }
    }

    override fun create(entity: E): E? {
        return transaction {
            repository.create(entity)
        }
    }

}
