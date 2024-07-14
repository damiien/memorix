package io.memorix.core.repository;

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.sql.SizedIterable

abstract class Repository<E: Entity<ID>, ID: Comparable<ID>> : IRepository<E, ID> {

    abstract override fun findAll(limit: Int): SizedIterable<E>

    abstract override fun findById(id: ID): E?

}
