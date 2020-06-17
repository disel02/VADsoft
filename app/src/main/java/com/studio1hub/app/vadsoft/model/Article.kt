package com.studio1hub.app.vadsoft.model

import androidx.room.*

@Entity
data class Article (

    @PrimaryKey
    val id: Int,
    val content: String,
    val comments: String,
    val likes: String,
    val createdAt: String,
    @Embedded
    val media: Media,
    @Embedded
    val user: UserArticle
)