package com.createfuture.coachingservice.handler

import com.createfuture.coachingservice.model.response.ApiErrorMessage
import com.createfuture.coachingservice.model.response.ErrorApiResponse
import com.createfuture.coachingservice.model.response.UserProfileApiResponse
import com.createfuture.coachingservice.model.response.UserProfileApiResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.time.Instant

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleTypeMismatch(ex: MethodArgumentTypeMismatchException): UserProfileApiResponseEntity {
        val response = UserProfileApiResponse(
            error = ErrorApiResponse(
                Instant.now(),
                ApiErrorMessage.InvalidRequest,
                ex.parameter.method?.name ?: "Method name unavailable"
            )
        )
        return UserProfileApiResponseEntity(response, HttpStatus.BAD_REQUEST)
    }
}
