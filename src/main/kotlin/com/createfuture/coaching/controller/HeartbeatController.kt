package com.createfuture.coaching.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HeartbeatController {
    @GetMapping("/heartbeat")
    fun heartbeat(): HttpStatus = HttpStatus.OK
}
