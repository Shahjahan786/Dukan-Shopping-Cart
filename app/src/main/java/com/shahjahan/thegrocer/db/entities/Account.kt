package com.shahjahan.thegrocer.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val price: Int)