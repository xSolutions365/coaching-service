package com.createfuture.coachingservice.model.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class UserProfileApiResponseEntity(
    body: UserProfileApiResponse,
    status: HttpStatus
) : ResponseEntity<UserProfileApiResponse>(body, status)
