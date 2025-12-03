package com.createfuture.coachingservice.controller

import com.createfuture.coachingservice.model.UserProfile
import com.createfuture.coachingservice.service.UserProfileService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@RestController
@RequestMapping("/api/v1/user")
class UserProfileController(private val userProfileService: UserProfileService) {

    @PostMapping("/seed")
    fun seedUsers(count: Int): List<UUID> {
        return userProfileService.seed(count);
    }

    @GetMapping("/random")
    fun getRandomUserProfile(): UserProfile {
        val user = userProfileService.getRandomProfile()
        userProfileService.setUser(user);

        return user
    }
}
