package com.createfuture.coachingservice.model.response

import java.util.UUID

sealed class ApiErrorMessage {
    data class UserNotFound(val userId: Any) : ApiErrorMessage()
    data class InvalidUuidFormat(val uuid: UUID) : ApiErrorMessage()
    data object InvalidRequest : ApiErrorMessage()

    override fun toString(): String = when (this) {
        is UserNotFound -> "User with ID [$userId] not found"
        is InvalidUuidFormat -> "Invalid UUID [$uuid] format"
        is InvalidRequest -> "Invalid request"
    }
}
