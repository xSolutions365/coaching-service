package com.createfuture.coachingservice.controller

import com.createfuture.coachingservice.model.NewUserProfile
import com.createfuture.coachingservice.model.UserProfile
import com.createfuture.coachingservice.service.UserProfileService
import com.createfuture.coachingservice.service.UserSeedService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.mockito.Mockito

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import org.springframework.http.HttpStatus
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.util.UUID

@SpringBootTest
class SeedControllerTest {

    @MockitoBean
    private lateinit var userProfileService: UserProfileService

    @MockitoBean
    private lateinit var userSeedService: UserSeedService

    @Autowired
    private lateinit var controller: SeedController

    private val expectedExceptionMessage = "Must generate between 1 and 1000 users at a time"

    @BeforeEach
    fun setUp() {
        Mockito.`when`(userSeedService.getRandomProfile())
            .thenReturn(getTestUserWithRandomUuid())
    }

    @Test
    fun loadsExpectedNumberOfUsers() {
        val expectedUsers = 10;

        val response = controller.seedUsers(expectedUsers)
        assert(response.statusCode == HttpStatus.OK)
        val uuidList = response.body as List<UUID>
        assert(uuidList.size == expectedUsers)
    }

    @Test
    fun doesNotLoadNegativeNumberOfUsers() {
        try {
            controller.seedUsers(-5)
            assert(false)
        } catch (e: IllegalArgumentException) {
            assert(e.message == expectedExceptionMessage)
        }
    }

    @Test
    fun doesNotLoadTooManyUsers() {
        try {
            controller.seedUsers(1005)
            assert(false)
        } catch (e: IllegalArgumentException) {
            assert(e.message == expectedExceptionMessage)
        }
    }

    @Test
    fun putRandomUserProfileReturnsUserProfileApiResponseEntity() {
        val response = controller.putRandomUserProfile()
        assert(response.statusCode == HttpStatus.OK)
        assertNotNull(response.body)
        assertNotNull(response.body?.userProfile)

        assertNotNull(response.body?.userProfile?.username)
        assertNotNull(response.body?.userProfile?.preferredName)
        assertNotNull(response.body?.userProfile?.slackUsername)
        assertNotNull(response.body?.userProfile?.email)
        assertNotNull(response.body?.userProfile?.phoneNumber)
        assertNotNull(response.body?.userProfile?.bio)
        assertNotNull(response.body?.userProfile?.profilePictureUrl)
    }

    val newUserProfile = NewUserProfile(
        username = "testUser",
        preferredName = "Test User",
        email = "testuser@example.com",
        slackUsername = "@testUser",
        phoneNumber = "123-456-7890",
        bio = "Just a test user :)",
        profilePictureUrl = "https://example.com/profile.jpg"
    )

    fun getTestUserWithRandomUuid(): UserProfile {
        return UserProfile(
            UUID.randomUUID(),
            newUserProfile.username,
            newUserProfile.preferredName,
            newUserProfile.slackUsername,
            newUserProfile.email,
            newUserProfile.phoneNumber,
            newUserProfile.bio,
            newUserProfile.profilePictureUrl
        )
    }
}
