package com.moralex.testtaskapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "number_facts")
data class NumberFactEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val text: String
)
