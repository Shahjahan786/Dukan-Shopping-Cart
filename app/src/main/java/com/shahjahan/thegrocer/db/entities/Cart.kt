package com.shahjahan.thegrocer.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cart constructor(
    @PrimaryKey
    val id: String,
    val title: String,
    val image: String,
    val price: String,
    val description: String
)