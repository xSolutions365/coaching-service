package com.createfuture.coachingservice.model

data class UserProfile(val username: String,
                       val preferredName: String,
                       val slackUsername: String?,
                       val email: String?,
                       val phoneNumber: String?,
                       val bio: String?,
                       val profilePictureUrl: String?)
{
    init {
        if ((slackUsername.isNullOrBlank()) && (email.isNullOrBlank()) && (phoneNumber.isNullOrBlank())) {
            throw IllegalArgumentException("At least one contact detail must be provided.")
        }
    }
}
