package com.robertomiranda.app.core.list

interface ListItem {
    fun getType(): Int

    fun getID(): Int

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}