package io.memorix.core.service

import io.memorix.core.entity.IEntity
import io.memorix.core.repository.IRepository

interface IService<R : IRepository<E, ID>, E : IEntity<E, ID>, ID : Comparable<ID>> {

    val repository: R

    fun findById(id: ID): E?

    fun create(entity: E): E?

}
