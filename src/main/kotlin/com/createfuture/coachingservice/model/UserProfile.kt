package com.createfuture.coachingservice.model

import com.createfuture.coachingservice.model.response.UserProfileApiResponse
import com.createfuture.coachingservice.model.response.UserProfileApiResponseEntity
import org.springframework.http.HttpStatus
import java.util.UUID

interface UserProfileAttributes {
    val username: String
    val preferredName: String
    val slackUsername: String?
    val email: String?
    val phoneNumber: String?
    val bio: String?
    val profilePictureUrl: String?

    fun validateContactInfo() {
        require(
            !(slackUsername.isNullOrBlank() &&
                    email.isNullOrBlank() &&
                    phoneNumber.isNullOrBlank())
        ) { "At least one contact detail must be provided." }
    }
}

data class NewUserProfile(
    override val username: String,
    override val preferredName: String,
    override val slackUsername: String?,
    override val email: String?,
    override val phoneNumber: String?,
    override val bio: String?,
    override val profilePictureUrl: String?
) : UserProfileAttributes {
    init {
        validateContactInfo()
    }
}

data class UserProfile(
    val id: UUID,
    override val username: String,
    override val preferredName: String,
    override val slackUsername: String? = null,
    override val email: String? = null,
    override val phoneNumber: String? = null,
    override val bio: String? = null,
    override val profilePictureUrl: String? = null
) : UserProfileAttributes {
    init {
        validateContactInfo()
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
