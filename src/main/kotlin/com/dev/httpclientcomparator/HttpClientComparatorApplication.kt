package com.dev.httpclientcomparator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients(basePackages = ["com.dev.httpclientcomparator.service"])
class HttpClientComparatorApplication

fun main(args: Array<String>) {
	runApplication<HttpClientComparatorApplication>(*args)
}
