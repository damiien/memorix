package io.memorix.core.service

import io.memorix.core.repository.IRepository
import org.jetbrains.exposed.dao.Entity

interface IService<R : IRepository<E, ID>, E : Entity<ID>, ID : Comparable<ID>> {

    val repository: R

    fun findById(id: ID): E?

}
