package com.createfuture.coachingservice.model.response

import com.createfuture.coachingservice.model.UserProfile
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserProfileApiResponse(
    val userProfile: UserProfile? = null,
    val userProfiles: List<UserProfile>? = null,
    val error: ErrorApiResponse? = null
)
