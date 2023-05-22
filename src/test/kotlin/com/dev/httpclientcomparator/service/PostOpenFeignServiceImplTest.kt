package com.dev.httpclientcomparator.service

import com.dev.httpclientcomparator.model.Post
import com.dev.httpclientcomparator.service.interfaces.PostOpenFeignClient
import feign.FeignException
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PostOpenFeignServiceImplTest {

    @InjectMockKs
    lateinit var sut: PostOpenFeignServiceImpl

    @MockK
    lateinit var client: PostOpenFeignClient

    @Test
    fun `it should return a post when client returns 200`() {
        // arrange
        every { client.get(any()) } returns Post(1, 1, "", "")

        // act
        val post = sut.get(1)

        // assert
        assertEquals(Post(1, 1, "", ""), post)
    }

    @Test
    fun `it should return a post when client returns 404`() {
        // arrange
        every { client.get(any()) } throws FeignException.NotFound(
            "message",
            mockk(),
            null,
            null
        )

        // act
        val post = sut.get(1000)

        // assert
        assertEquals(Post(0, 0, "", ""), post)
    }

    @Test
    fun `it should throw exception when is not a client error`() {
        // arrange
        every { client.get(any()) } throws FeignException.InternalServerError(
            "message",
            mockk(),
            null,
            null
        )

        // act
        val exception = assertThrows(Exception::class.java) {
            sut.get(1000)
        }

        // assert
        assertEquals("Erro", exception.message)
    }
}