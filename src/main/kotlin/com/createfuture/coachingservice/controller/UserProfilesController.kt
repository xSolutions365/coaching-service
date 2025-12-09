package com.createfuture.coachingservice.controller

import com.createfuture.coachingservice.model.response.UserProfileApiResponseEntity
import com.createfuture.coachingservice.model.toApiResponseEntity
import com.createfuture.coachingservice.service.UserProfileService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/api/v1/users")
class UserProfilesController(private val userProfileService: UserProfileService) {

    @GetMapping("/{page}")
    fun getAllUsers(@PathVariable page: Int): UserProfileApiResponseEntity {
        require(page < 0) { "Page number cannot be negative" }
        println("Fetching user profiles for page: $page")

        val users = userProfileService.getUsers(page)
        return users.toApiResponseEntity()
    }
}
