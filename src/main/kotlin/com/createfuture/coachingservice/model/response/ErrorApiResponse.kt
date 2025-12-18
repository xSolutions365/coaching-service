package com.createfuture.coachingservice.model.response

data class ErrorApiResponse(
    val timestamp: java.time.Instant,
    val error: String,
    val path: String?
) {
    companion object {
        fun fromApiErrorMessage(
            timestamp: java.time.Instant,
            error: ApiErrorMessage,
            path: String?
        ) = ErrorApiResponse(timestamp, error.toString(), path)
    }
}
