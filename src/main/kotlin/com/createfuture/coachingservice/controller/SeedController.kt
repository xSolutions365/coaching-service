package com.createfuture.coachingservice.controller

import com.createfuture.coachingservice.model.response.UserProfileApiResponseEntity
import com.createfuture.coachingservice.model.toApiResponseEntity
import com.createfuture.coachingservice.service.UserProfileService
import com.createfuture.coachingservice.service.UserSeedService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/seed")
class SeedController(
    private val userProfileService: UserProfileService,
    private val userSeedService: UserSeedService
) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(SeedController::class.java)
    }

    @PostMapping("/users")
    fun seedUsers(@RequestParam count: Int): ResponseEntity<List<UUID>> {
        require(count in 1..1000) { "Must generate between 1 and 1000 users at a time" }
        logger.info("Seeding database with $count user profiles")

        val createdUserIds = mutableListOf<UUID>()

        repeat(count) {
            val user = userSeedService.getRandomProfile()
            userProfileService.setUser(user)
            createdUserIds.add(user.id)
        }

        return ResponseEntity(createdUserIds, HttpStatus.OK)
    }

    @PostMapping("/user")
    fun createRandomUserProfile(): UserProfileApiResponseEntity {
        logger.info("Creating random user profile")
        val user = userSeedService.getRandomProfile()
        userProfileService.setUser(user)

        return user.toApiResponseEntity()
    }
}
