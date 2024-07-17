package io.memorix.core.data

import io.memorix.core.entity.IEntity

abstract class DataObject<D: DataObject<D, E>, E : IEntity<E, *>> {

    abstract fun toEntity(): E;

    @Throws(IllegalStateException::class)
    abstract fun validate()

    companion object {
        val NOT_BLANK_REGEX: String  = "^\\S+$"
    }

}
