package com.watkins.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO: Remove
@Entity
data class FakeEntity(
    @PrimaryKey
    val number: Int
)