package com.dev.httpclientcomparator.service.interfaces

import com.dev.httpclientcomparator.model.Post

interface PostService {

    fun get(id: Int): Post
}