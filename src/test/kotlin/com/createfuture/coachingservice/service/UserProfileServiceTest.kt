package com.createfuture.coachingservice.service

import com.createfuture.coachingservice.model.NewUserProfile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.UUID

class UserProfileServiceTest {

    private lateinit var service: UserProfileService

    @BeforeEach
    fun setUp() {
        service = UserProfileService()
    }

    @Test
    fun `createUser should add and return new user`() {
        val newUser = NewUserProfile(
            "user1",
            "User One",
            "slack1",
            "user1@example.com",
            "123456",
            "bio",
            "picUrl"
        )
        val created = service.createUser(newUser)
        Assertions.assertNotNull(created.id)
        Assertions.assertEquals(newUser.username, created.username)
        Assertions.assertEquals(created, service.getUser(created.id))
    }

    @Test
    fun `getUser should return null for unknown id`() {
        Assertions.assertNull(service.getUser(UUID.randomUUID()))
    }

    @Test
    fun `updateUser should update existing user`() {
        val newUser = NewUserProfile(
            "user2",
            "User Two",
            "slack2",
            "user2@example.com",
            "654321",
            "bio2",
            "picUrl2"
        )
        val created = service.createUser(newUser)
        val updated = NewUserProfile(
            "user2a",
            "User Two A",
            "slack2a",
            "user2a@example.com",
            "000000",
            "bio2a",
            "picUrl2a"
        )
        val result = service.updateUser(created.id, updated)
        Assertions.assertNotNull(result)
        Assertions.assertEquals("user2a", result?.username)
    }

    @Test
    fun `deleteUser should remove user`() {
        val newUser = NewUserProfile(
            "user3",
            "User Three",
            "slack3",
            "user3@example.com",
            "333333",
            "bio3",
            "picUrl3"
        )
        val created = service.createUser(newUser)
        service.deleteUser(created.id)
        Assertions.assertNull(service.getUser(created.id))
    }

    @Test
    fun `getUsers should paginate correctly`() {
        repeat(150) {
            service.createUser(
                NewUserProfile(
                    "user$it",
                    "User $it",
                    "slack$it",
                    "user$it@example.com",
                    "$it",
                    "bio$it",
                    "picUrl$it"
                )
            )
        }
        val page1 = service.getUsers(1)
        val page2 = service.getUsers(2)
        Assertions.assertEquals(100, page1.size)
        Assertions.assertEquals(50, page2.size)
    }
}
