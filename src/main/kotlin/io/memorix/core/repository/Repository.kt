package io.memorix.core.repository;

import io.memorix.core.entity.IEntity

abstract class Repository<E: IEntity<E, ID>, ID> : IRepository<E, ID> {

    abstract override fun findAll(limit: Int): List<E>

    abstract override fun findById(id: ID): E?

    abstract override fun create(entity: E): E?

}
