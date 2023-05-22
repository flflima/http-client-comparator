package com.dev.httpclientcomparator.controller

import com.dev.httpclientcomparator.model.Post
import com.dev.httpclientcomparator.service.interfaces.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("posts")
class PostController(@Autowired private val service: PostService) {

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<Post> {
        return ResponseEntity.ok(service.get(id))
    }
}