package com.dev.httpclientcomparator.service.interfaces

import com.dev.httpclientcomparator.model.Post
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(name = "posts", url = "\${service.url}")
interface PostOpenFeignClient {

    @RequestMapping(method = [RequestMethod.GET], value = ["/posts/{id}"])
    fun get(@PathVariable id: Int): Post
}
