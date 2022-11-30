package ru.netology.nmedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private val empty = Post(
    id = 0,
    author = "" ,
    content = "",
    published = "",
    likedByMe = false,
    video = null
)

class PostViewModel() : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun save() {
        edited.value?.let {
            repository.save(it) }
        edited.value = empty
    }

    fun edit(post:Post) {
        edited.value = post
    }

    fun changeContent(content: String) {
        val text = content.trim()
        if(edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }

    fun close() {
        edited.value = empty
    }
    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)
}