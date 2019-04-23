package com.watkins.lisa.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO: Remove
@Entity
data class FakeEntity(
    @PrimaryKey
    val number: Int
)