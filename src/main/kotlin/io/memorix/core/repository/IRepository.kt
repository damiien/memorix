package io.memorix.core.repository

import io.memorix.core.entity.IEntity

interface IRepository<E: IEntity<E, ID>, ID> {

    fun findAll(limit: Int = Int.MAX_VALUE): List<E>

    fun findById(id: ID): E?

    fun create(entity: E): E?

}
