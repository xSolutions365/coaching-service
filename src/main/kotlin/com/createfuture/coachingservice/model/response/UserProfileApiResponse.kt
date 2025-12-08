package com.createfuture.coachingservice.model.response

import com.createfuture.coachingservice.model.UserProfile
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserProfileApiResponse(val userProfile: UserProfile? = null,
                                  val userProfiles: List<UserProfile>? = null,
                                  val error: ErrorApiResponse? = null)
