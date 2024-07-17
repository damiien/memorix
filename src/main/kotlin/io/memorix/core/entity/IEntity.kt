package io.memorix.core.entity

interface IEntity<E: IEntity<E, ID>, ID> {

    var id: ID

}
