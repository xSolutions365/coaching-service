package com.createfuture.coachingservice.controller

import com.createfuture.coachingservice.model.NewUserProfile
import com.createfuture.coachingservice.model.response.UserProfileApiResponseEntity
import com.createfuture.coachingservice.model.toApiResponseEntity
import com.createfuture.coachingservice.service.UserProfileService
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
class UserProfileController(private val userProfileService: UserProfileService) {

    @PostMapping
    fun createUserProfile(newUser: NewUserProfile): UserProfileApiResponseEntity {
        println("Creating new user profile")

        val user = userProfileService.createUser(newUser)
        return user.toApiResponseEntity()
    }

    @PutMapping("/{userId}")
    fun updateUserProfile(
        @PathVariable userId: UUID,
        updatedUser: NewUserProfile
    ): UserProfileApiResponseEntity {
        println("Updating user profile for userId: $userId")
        return userProfileService.updateUser(userId, updatedUser).toApiResponseEntity()
    }

    @GetMapping("/{userId}")
    fun getUserProfile(@PathVariable userId: UUID): UserProfileApiResponseEntity {
        println("Fetching user profile for userId: $userId")

        val user = userProfileService.getUser(userId)
        return user.toApiResponseEntity()
    }

    @DeleteMapping("/{userId}")
    fun deleteUserProfile(@PathVariable userId: UUID): ResponseEntity<Unit> {
        println("Deleting user profile for userId: $userId")
        userProfileService.deleteUser(userId)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
