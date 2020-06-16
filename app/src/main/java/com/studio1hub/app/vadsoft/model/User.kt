package com.studio1hub.app.vadsoft.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (

    @PrimaryKey
    val id: Int,
    val name: String,
    val avatar: String,
    val lastname: String,
    val city: String,
    val designation: String,
    val about: String,
    val createdAt: String
)