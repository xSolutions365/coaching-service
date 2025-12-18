package com.createfuture.coachingservice.controller

import com.createfuture.coachingservice.model.NewUserProfile
import com.createfuture.coachingservice.model.UserProfile
import com.createfuture.coachingservice.model.response.UserProfileApiResponse
import com.createfuture.coachingservice.service.UserProfileService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.any

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import org.springframework.http.HttpStatus
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.util.UUID
import kotlin.assert

@SpringBootTest
class UserProfileControllerTest {

    @MockitoBean
    private lateinit var userProfileService: UserProfileService

    @Autowired
    private lateinit var controller: UserProfileController

    val validUuid: UUID = UUID.fromString("95a0d3ef-1feb-4438-9f0b-76f577b61f7a")
    val nullApiResponseBody: UserProfileApiResponse = UserProfileApiResponse()

    val testNewUserProfile = NewUserProfile(
        username = "testUser",
        preferredName = "Test User",
        email = "testuser@example.com",
        slackUsername = "@testUser",
        phoneNumber = "123-456-7890",
        bio = "Just a test user :)",
        profilePictureUrl = "https://example.com/profile.jpg"
    )

    val testUserProfile = UserProfile(
        validUuid,
        testNewUserProfile.username,
        testNewUserProfile.preferredName,
        testNewUserProfile.slackUsername,
        testNewUserProfile.email,
        testNewUserProfile.phoneNumber,
        testNewUserProfile.bio,
        testNewUserProfile.profilePictureUrl
    )


    // -- GET --

    @Test
    fun getUserProfileRespondsWithNullResponseWhenUserNotFound() {
        Mockito.`when`(userProfileService.getUser(validUuid))
            .thenReturn(null)

        val response = controller.getUserProfile(validUuid)
        assertNotNull(response)
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertEquals(nullApiResponseBody, response.body)
    }

    @Test
    fun getUserProfileRespondsWithUserProfileWhenFound() {
        Mockito.`when`(userProfileService.getUser(validUuid))
            .thenReturn(testUserProfile)

        val response = controller.getUserProfile(validUuid)

        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(testUserProfile, response.body?.userProfile)
        assertEquals(null, response.body?.userProfiles)
    }

    @Test
    fun getUserProfileRespondsWithErrorWhenInvalidUuidProvided() {
        // TODO("This might only be possible in a more e2e test?")
    }

    // -- END GET --

    // -- POST --

    @Test
    fun createUserProfileRespondsWithErrorWhenInvalidUserProvided() {
        try {
            val response = controller.createUserProfile(
                NewUserProfile(
                    "Steve", "Steve",
                    slackUsername = null,
                    email = null,
                    phoneNumber = null,
                    bio = null,
                    profilePictureUrl = null
                )
            )
            assert(false)
        } catch (e: IllegalArgumentException) {
            assertEquals("At least one contact detail must be provided.", e.message)
        }
    }

    @Test
    fun createUserProfileRespondsWithUserWhenValidUserProvided() {

        Mockito.`when`(userProfileService.createUser(any())).thenReturn(testUserProfile)

        val response = controller.createUserProfile(testNewUserProfile)

        assertNotNull(response)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(testUserProfile, response.body?.userProfile)
        assertEquals(null, response.body?.userProfiles)
    }

    // -- END POST --

    // -- PUT --

    @Test
    fun updateUserProfileRespondsWithUserWhenValidUserProvided() {

        Mockito.`when`(userProfileService.updateUser(any(), any())).thenReturn(testUserProfile)

        val response = controller.updateUserProfile(validUuid, testNewUserProfile)

        assertNotNull(response)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(testUserProfile, response.body?.userProfile)
        assertEquals(null, response.body?.userProfiles)
    }

    // -- END PUT --

    // -- DELETE --

    @Test
    fun deleteUserProfileRespondsWithNoContentWhenUserDeleted() {
        Mockito.doNothing().`when`(userProfileService).deleteUser(validUuid)

        val response = controller.deleteUserProfile(validUuid)

        assertNotNull(response)
        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
    }

    // -- END DELETE --
}
