package com.createfuture.coachingservice.controller

import com.createfuture.coachingservice.service.UserProfileService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/api/v1/user")
class UserProfileController(private val userProfileService: UserProfileService) {

}
