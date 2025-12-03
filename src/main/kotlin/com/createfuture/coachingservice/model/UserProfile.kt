package com.createfuture.coachingservice.model

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
