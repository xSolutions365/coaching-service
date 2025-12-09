package com.createfuture.coachingservice.controller

import com.createfuture.coachingservice.model.UserProfile
import com.createfuture.coachingservice.model.response.UserProfileApiResponseEntity
import com.createfuture.coachingservice.model.toApiResponseEntity
import com.createfuture.coachingservice.service.UserProfileService
import com.createfuture.coachingservice.service.UserSeedService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/seed")
class SeedController(
    private val userProfileService: UserProfileService,
    private val userSeedService: UserSeedService
) {

    @PostMapping("/users")
    fun seedUsers(count: Int): ResponseEntity<List<UUID>> {
        println("Seeding database with $count user profiles")

        val tempUserCache = mutableMapOf<UUID, UserProfile>()

        for (i in 1..count) {
            val user = userSeedService.getRandomProfile()
            tempUserCache[user.id] = user
            userProfileService.setUser(user)
        }

        return ResponseEntity(tempUserCache.keys.toList(), HttpStatus.OK)
    }

    @PostMapping("/user")
    fun putRandomUserProfile(): UserProfileApiResponseEntity {
        println("Create random user profile")
        val user = userSeedService.getRandomProfile()
        userProfileService.setUser(user)

        return user.toApiResponseEntity()
    }
}
