package com.createfuture.coachingservice.controller

import com.createfuture.coachingservice.controller.UserProfilesController
import com.createfuture.coachingservice.model.UserProfile
import com.createfuture.coachingservice.service.UserProfileService
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest
class UserProfilesControllerTest {

    @MockitoBean
    private lateinit var userProfileService: UserProfileService

    @Autowired
    private lateinit var controller: UserProfilesController

    @Test
    fun paginationUnderZeroFails() {
        try {
            controller.getAllUsers(-1)
            assert(false)
        } catch (e: IllegalArgumentException) {
            val expectedMessage = "Page number cannot be negative"
            assert(e.message == expectedMessage)
        }
    }

    @Test
    fun paginationOverZeroSucceeds() {
        val mockList = emptyList<UserProfile>()
        whenever(userProfileService.getUsers(any())).thenReturn(mockList)

        val response = controller.getAllUsers(1)
        assert(response.body?.userProfiles?.isEmpty() ?: false)
    }
}
