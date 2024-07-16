package io.memorix.core.entity

interface IEntity<E: Entity<E, ID>, ID> {

    var id: ID

}
