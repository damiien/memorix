package io.memorix.core.entity

abstract class Entity<T: Entity<T, ID>, ID> : IEntity<T, ID>   {

    override abstract var id: ID

}
