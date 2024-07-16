package io.memorix.core.service

import io.memorix.core.entity.Entity
import io.memorix.core.repository.IRepository

interface IService<R : IRepository<E, ID>, E : Entity<E, ID>, ID : Comparable<ID>> {

    val repository: R

    fun findById(id: ID): E?

    fun create(entity: E): E?

}
