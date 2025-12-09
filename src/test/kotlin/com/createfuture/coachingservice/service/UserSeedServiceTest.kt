package com.createfuture.coachingservice.service

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach


class UserSeedServiceTest {

    private lateinit var service: UserSeedService

    @BeforeEach
    fun setUp() {
        service = UserSeedService()
    }

    @Test
    fun `getRandomName returns name from resources`() {
        val result = service.getRandomName()
        assertTrue(result.matches(Regex("^\\w+ \\w+$")))
    }

    @Test
    fun `getRandomProfile returns valid UserProfile`() {
        val profile = service.getRandomProfile()
        assertNotNull(profile.id)
        assertTrue(profile.id.toString().isNotBlank())
        assertTrue(profile.username.isNotBlank())
        assertTrue(profile.preferredName.isNotBlank())
        assertTrue(profile.username.isNotBlank())
        assertTrue(profile.email?.matches(Regex("^[\\w.]+@createfuture.com$")) ?: false)
    }
}
