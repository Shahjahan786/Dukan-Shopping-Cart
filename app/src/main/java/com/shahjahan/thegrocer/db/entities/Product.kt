package com.shahjahan.thegrocer.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Product constructor(
    @PrimaryKey
    val id: String,
    var title: String,
    val image: String,
    val description: String,
    val price: String,
    var category: String
    )
