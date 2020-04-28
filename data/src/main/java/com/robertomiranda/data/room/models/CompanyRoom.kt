package com.robertomiranda.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "companies")
data class CompanyRoom(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "bs")
    val bs: String,

    @ColumnInfo(name = "catchPhrase")
    val catchPhrase: String,

    @ColumnInfo(name = "name")
    val name: String
)