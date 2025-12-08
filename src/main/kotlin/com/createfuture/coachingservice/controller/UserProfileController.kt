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
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@RestController
@RequestMapping("/api/v1/user")
class UserProfileController(private val userProfileService: UserProfileService) {

    @GetMapping("/{userId}")
    fun getUserProfile(@PathVariable userId: UUID): UserProfile? {
        System.out.println("Fetching user profile for userId: $userId")

        return userProfileService.getUser(userId);
    }

    @GetMapping("/all")
    fun getAllUsers(): List<UserProfile> {
       return userProfileService.getUsers()
    }

    @PostMapping("/seed")
    fun seedUsers(count: Int): List<UUID> {
        return userProfileService.seed(count);
    }

    @DeleteMapping("/{userId}")
    fun deleteUserProfile(@PathVariable userId: UUID) {
        println("Deleting user profile for userId: $userId")

        userProfileService.deleteUser(userId)
    }

    @GetMapping("/random")
    fun getRandomUserProfile(): UserProfile {
        val user = userProfileService.getRandomProfile()
        userProfileService.setUser(user);

        return user
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
