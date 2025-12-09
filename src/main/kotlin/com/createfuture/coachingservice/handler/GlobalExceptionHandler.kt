package com.createfuture.coachingservice.handler

import com.createfuture.coachingservice.controller.UserProfilesController
import com.createfuture.coachingservice.model.response.ApiErrorMessage
import com.createfuture.coachingservice.model.response.ErrorApiResponse
import com.createfuture.coachingservice.model.response.UserProfileApiResponse
import com.createfuture.coachingservice.model.response.UserProfileApiResponseEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.time.Instant

@ControllerAdvice
class GlobalExceptionHandler {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleTypeMismatch(ex: MethodArgumentTypeMismatchException): UserProfileApiResponseEntity {
        logger.warn("Type mismatch error: ${ex.message}", ex)

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
