package com.createfuture.coachingservice.controller

import com.createfuture.coachingservice.model.UserProfile
import com.createfuture.coachingservice.model.response.UserProfileApiResponse
import com.createfuture.coachingservice.model.response.UserProfileApiResponseEntity
import com.createfuture.coachingservice.service.UserProfileService
import org.springframework.http.HttpStatus
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
class UserProfileController(private val userProfileService: UserProfileService) {

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

    @PostMapping("/seed")
    fun seedUsers(count: Int): List<UUID> {
        println("Seeding database with $count user profiles")

        return userProfileService.seed(count)
    }

    @DeleteMapping("/{userId}")
    fun deleteUserProfile(@PathVariable userId: UUID) {
        println("Deleting user profile for userId: $userId")

        userProfileService.deleteUser(userId)
    }

    @PutMapping("/random")
    fun putRandomUserProfile(): UserProfileApiResponseEntity {
        println("Create random user profile")
        val user = userProfileService.getRandomProfile()
        userProfileService.setUser(user);

        return user.toApiResponseEntity();
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
