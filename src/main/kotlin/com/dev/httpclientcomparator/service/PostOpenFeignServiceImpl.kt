package com.dev.httpclientcomparator.service

import com.dev.httpclientcomparator.model.Post
import com.dev.httpclientcomparator.service.interfaces.PostOpenFeignClient
import com.dev.httpclientcomparator.service.interfaces.PostService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import feign.FeignException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostOpenFeignServiceImpl(@Autowired private val client: PostOpenFeignClient) : PostService {
    override fun get(id: Int): Post {
        val post = try {
            client.get(id)
        } catch (e: FeignException) {
            println(e.status())
            println(e.responseBody())
            if (e.contentUTF8().trim().isNotBlank() && e.contentUTF8().trim() != "{}") {
                println(
                    ObjectMapper().registerKotlinModule()
                        .readValue(e.contentUTF8(), Post::class.java)
                )
            }

            if (e.status() in 400..499) {
                Post(0, 0, "", "")
            } else {
                throw Exception("Erro")
            }
        }
        return post
    }
}