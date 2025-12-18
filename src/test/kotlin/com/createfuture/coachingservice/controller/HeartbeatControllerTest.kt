package com.createfuture.coachingservice.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HeartbeatControllerTest {

    @Autowired
    private lateinit var controller: HeartbeatController


    @Test
    fun contextLoads() {
        assertThat(controller).isNotNull()
    }
}
