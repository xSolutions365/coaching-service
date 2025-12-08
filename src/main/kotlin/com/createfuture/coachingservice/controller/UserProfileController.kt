package com.createfuture.coachingservice.controller

import com.createfuture.coachingservice.model.UserProfile
import com.createfuture.coachingservice.model.response.UserProfileApiResponse
import com.createfuture.coachingservice.model.response.UserProfileApiResponseEntity
import com.createfuture.coachingservice.service.UserProfileService
import com.createfuture.coachingservice.service.UserSeedService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@RestController
@RequestMapping("/api/v1/user")
class UserProfileController(private val userProfileService: UserProfileService,
                            private val userSeedService: UserSeedService) {

    @GetMapping("/{userId}")
    fun getUserProfile(@PathVariable userId: UUID): UserProfileApiResponseEntity {
        println("Fetching user profile for userId: $userId")

        val user = userProfileService.getUser(userId)
        return user.toApiResponseEntity()
    }

    @GetMapping("/all/{page}")
    fun getAllUsers(@PathVariable page: Int): UserProfileApiResponseEntity {
        println("Fetching all user profiles")

        val users = userProfileService.getUsers(page)
        return users.toApiResponseEntity()
    }

    @DeleteMapping("/{userId}")
    fun deleteUserProfile(@PathVariable userId: UUID) {
        println("Deleting user profile for userId: $userId")

        userProfileService.deleteUser(userId)
    }

    @PostMapping("/seed")
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

    @PutMapping("/random")
    fun putRandomUserProfile(): UserProfileApiResponseEntity {
        println("Create random user profile")
        val user = userSeedService.getRandomProfile()
        userProfileService.setUser(user)

        return user.toApiResponseEntity()
    }

    // -- Extension Functions --

    fun UserProfile?.toApiResponseEntity(): UserProfileApiResponseEntity =
        UserProfileApiResponseEntity(
            UserProfileApiResponse(userProfile = this),
            if (this != null) HttpStatus.OK else HttpStatus.NOT_FOUND
        )

    fun List<UserProfile>?.toApiResponseEntity(): UserProfileApiResponseEntity =
        UserProfileApiResponseEntity(
            UserProfileApiResponse(userProfiles = this),
            if (this != null) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
}
