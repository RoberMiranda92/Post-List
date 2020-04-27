package com.robertomiranda.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "companies")
data class CompanyRoom(

    @ColumnInfo(name = "bs")
    val bs: String? = null,

    @ColumnInfo(name = "catchPhrase")
    val catchPhrase: String? = null,

    @ColumnInfo(name = "name")
    val name: String? = null
)