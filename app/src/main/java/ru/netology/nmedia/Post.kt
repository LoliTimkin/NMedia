package ru.netology.nmedia

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    val sharedByMe: Boolean = false,
    var likes: Int = 0,
    var shared: Int = 0,

    )