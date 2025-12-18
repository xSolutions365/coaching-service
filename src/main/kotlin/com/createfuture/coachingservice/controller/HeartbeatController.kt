package com.createfuture.coachingservice.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/heartbeat")
class HeartbeatController {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(HeartbeatController::class.java)
    }

    @GetMapping("/")
    fun heartbeat(): HttpStatus {
        logger.info("Th'thump... Heartbeat check received!")
        return HttpStatus.OK
    }
}
