package io.memorix.core.repository

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.sql.SizedIterable

interface IRepository<E: Entity<ID>, ID: Comparable<ID>> {

    fun findAll(limit: Int = Int.MAX_VALUE): SizedIterable<E>

    fun findById(id: ID): E?

}
