package com.createfuture.coachingservice.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.UUID

class UserProfileTest {

    val id: UUID = UUID.fromString("b4e5009e-6ace-46a1-b044-9f5923fa7f12")
    val username = "testUser"
    val preferredName = "Test User"
    val email = "testuser@example.com"
    val slackUsername = "@testUser"
    val phoneNumber = "123-456-7890"
    val bio = "Just a test user :)"
    val profilePictureUrl = "https://example.com/profile.jpg"

    @Test
    fun userProfileCreatedNormally() {
        val userProfile = UserProfile(
            id, username, preferredName, slackUsername, email, phoneNumber, bio, profilePictureUrl
        )

        assertEquals(userProfile.id, userProfile.id)
        assertEquals(userProfile.username, username)
        assertEquals(userProfile.preferredName, preferredName)
        assertEquals(userProfile.email, email)
        assertEquals(userProfile.slackUsername, slackUsername)
        assertEquals(userProfile.phoneNumber, phoneNumber)
        assertEquals(userProfile.bio, bio)
        assertEquals(userProfile.profilePictureUrl, profilePictureUrl)
    }

    @Test
    fun userProfileCreatedThrowsWithNoContactInfo() {
        try {
            UserProfile(
                id, username, preferredName, null, null, null, bio, profilePictureUrl
            )
            assert(false)
        } catch (e: IllegalArgumentException) {
            assert(e.message == "At least one contact detail must be provided.")
        }
    }

    @Test
    fun extensionFunctionToApiResponseEntityReturnsNotNull() {
        val userProfile = UserProfile(
            id, username, preferredName, email, slackUsername, phoneNumber, bio, profilePictureUrl
        )

        val responseEntity = userProfile.toApiResponseEntity()
        assertEquals(responseEntity.statusCode.value(), 200)
        assertEquals(responseEntity.body?.userProfile, userProfile)
    }

    @Test
    fun extensionFunctionToApiResponseEntityWithNullUserProfileReturnsNotFound() {
        val userProfile: UserProfile? = null

        val responseEntity = userProfile.toApiResponseEntity()
        
        assertEquals(responseEntity.statusCode.value(), 404)
        assertEquals(responseEntity.body?.userProfile, null)
    }

    @Test
    fun extensionFunctionListToApiResponseEntityReturnsNotNull() {
        val profile = UserProfile(
            id, username, preferredName, email, slackUsername, phoneNumber, bio, profilePictureUrl
        )

        val userProfileList: List<UserProfile> = listOf(profile, profile, profile)
        val responseEntity = userProfileList.toApiResponseEntity()

        assertEquals(responseEntity.statusCode.value(), 200)
        assertEquals(responseEntity.body?.userProfiles, userProfileList)
    }
}
