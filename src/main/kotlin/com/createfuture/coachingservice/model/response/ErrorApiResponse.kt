package com.createfuture.coachingservice.model.response

data class ErrorApiResponse(
    val timestamp: java.time.Instant,
    val error: ApiErrorMessage,
    val path: String?
)
