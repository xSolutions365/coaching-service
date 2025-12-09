package com.createfuture.coachingservice.controller

import com.createfuture.coachingservice.model.NewUserProfile
import com.createfuture.coachingservice.model.UserProfile
import com.createfuture.coachingservice.model.response.UserProfileApiResponseEntity
import com.createfuture.coachingservice.model.toApiResponseEntity
import com.createfuture.coachingservice.service.UserProfileService
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

    @PostMapping
    fun createUserProfile(newUser: NewUserProfile): UserProfileApiResponseEntity {
        println("Creating new user profile")

        val user = userProfileService.createUser(newUser)
        return user.toApiResponseEntity()
    }

    @GetMapping("/{userId}")
    fun getUserProfile(@PathVariable userId: UUID): UserProfileApiResponseEntity {
        println("Fetching user profile for userId: $userId")

        val user = userProfileService.getUser(userId)
        return user.toApiResponseEntity()
    }

    @DeleteMapping("/{userId}")
    fun deleteUserProfile(@PathVariable userId: UUID) {
        println("Deleting user profile for userId: $userId")

        userProfileService.deleteUser(userId)
    }


    @GetMapping("/all/{page}")
    fun getAllUsers(@PathVariable page: Int): UserProfileApiResponseEntity {
        println("Fetching all user profiles")

        val users = userProfileService.getUsers(page)
        return users.toApiResponseEntity()
    }
}
