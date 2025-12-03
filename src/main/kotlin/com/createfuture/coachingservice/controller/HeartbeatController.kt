package com.createfuture.coachingservice.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/heartbeat")
class HeartbeatController {
    @GetMapping("/")
    fun heartbeat(): HttpStatus = HttpStatus.OK
}
