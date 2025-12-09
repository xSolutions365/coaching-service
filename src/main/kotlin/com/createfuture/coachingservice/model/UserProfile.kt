package com.createfuture.coachingservice.model

import com.createfuture.coachingservice.model.response.UserProfileApiResponse
import com.createfuture.coachingservice.model.response.UserProfileApiResponseEntity
import org.springframework.http.HttpStatus
import java.util.UUID

data class UserProfile(val id: UUID,
                       val username: String,
                       val preferredName: String,
                       val slackUsername: String? = null,
                       val email: String? = null,
                       val phoneNumber: String? = null,
                       val bio: String? = null,
                       val profilePictureUrl: String? = null)
{
    init {
        if ((slackUsername.isNullOrBlank()) && (email.isNullOrBlank()) && (phoneNumber.isNullOrBlank())) {
            throw IllegalArgumentException("At least one contact detail must be provided.")
        }
    }
}

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

