package com.createfuture.coachingservice.handler

import com.createfuture.coachingservice.model.response.ApiErrorMessage
import com.createfuture.coachingservice.model.response.ErrorApiResponse
import com.createfuture.coachingservice.model.response.UserProfileApiResponse
import com.createfuture.coachingservice.model.response.UserProfileApiResponseEntity
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.time.Instant
import kotlin.toString

@ControllerAdvice
class GlobalExceptionHandler {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    fun buildResponse(
        error: ApiErrorMessage,
        status: HttpStatus,
        request: HttpServletRequest
    ): UserProfileApiResponseEntity {
        val response = UserProfileApiResponse(
            error = ErrorApiResponse.fromApiErrorMessage(
                Instant.now(),
                error,
                request.requestURI
            )
        )
        return UserProfileApiResponseEntity(response, status)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleTypeMismatch(
        ex: MethodArgumentTypeMismatchException,
        request: HttpServletRequest
    ): UserProfileApiResponseEntity {
        logger.warn("Type mismatch error: ${ex.message}", ex)

        val error = ApiErrorMessage.TypeMismatch(
            parameterName = ex.parameter.parameterName ?: "Unknown parameter",
            expectedType = ex.requiredType?.simpleName ?: "Unknown type",
            receivedValue = ex.value?.toString() ?: "null"
        )

        return buildResponse(error, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(
        ex: IllegalArgumentException,
        request: HttpServletRequest
    ): UserProfileApiResponseEntity {
        logger.warn("Invalid argument error: ${ex.message}", ex)

        val error = ApiErrorMessage.InvalidArgument(ex.cause.toString())
        return buildResponse(error, HttpStatus.BAD_REQUEST, request)
    }
}
