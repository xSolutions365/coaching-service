package com.createfuture.coachingservice.model.response

import java.util.UUID

sealed class ApiErrorMessage {
    class UserNotFound(val userId: UUID) : ApiErrorMessage()
    class InvalidUuidFormat(val uuid: String) : ApiErrorMessage()
    class InvalidArgument(val message: String) : ApiErrorMessage()
    class TypeMismatch(
        val parameterName: String,
        val expectedType: String,
        val receivedValue: String
    ) : ApiErrorMessage()

    class InvalidRequest : ApiErrorMessage()

    override fun toString(): String = "${this::class.simpleName}: " + when (this) {
        is UserNotFound -> userId
        is InvalidUuidFormat -> uuid
        is InvalidArgument -> "Invalid argument: $message"
        is TypeMismatch -> "Type mismatch for $parameterName (expected type $expectedType, received '$receivedValue')"
        is InvalidRequest -> "Invalid request"
    }
}
