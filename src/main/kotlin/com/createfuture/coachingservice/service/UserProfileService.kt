package com.createfuture.coachingservice.service

import com.createfuture.coachingservice.model.NewUserProfile
import com.createfuture.coachingservice.model.UserProfile
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.UUID.randomUUID

@Service
class UserProfileService {

    // TODO: This should interact with persistence, somewhere
    private var userCache = mutableMapOf<UUID, UserProfile>()
    private val pageSize = 100

    fun deleteUser(userId: UUID): Unit {
        userCache.remove(userId)
    }

    fun getUsers(page: Int): List<UserProfile> {
        val startIndex = (page - 1) * pageSize
        val endIndex = (startIndex + pageSize).coerceAtMost(userCache.size)

        if (startIndex >= userCache.size) {
            return emptyList()
        }

        return userCache.values.toList().subList(startIndex, endIndex)
    }

    fun getUser(userId: UUID): UserProfile? {
        return userCache[userId]
    }

    fun setUser(userProfile: UserProfile) {
        userCache[userProfile.id] = userProfile
    }

    fun createUser(userProfile: NewUserProfile): UserProfile {
        val newUser = UserProfile(
            id = randomUUID(),
            username = userProfile.username,
            preferredName = userProfile.preferredName,
            slackUsername = userProfile.slackUsername,
            email = userProfile.email,
            phoneNumber = userProfile.phoneNumber,
            bio = userProfile.bio,
            profilePictureUrl = userProfile.profilePictureUrl
        )

        insertUser(newUser)
        return newUser
    }

    fun insertUser(userProfile: UserProfile) {
        userCache[userProfile.id] = userProfile
    }

    fun updateUser(userId: UUID, updatedUser: NewUserProfile): UserProfile? {
        val existingUser = userCache[userId] ?: return null

        val newUserProfile = UserProfile(
            id = existingUser.id,
            username = updatedUser.username,
            preferredName = updatedUser.preferredName,
            slackUsername = updatedUser.slackUsername,
            email = updatedUser.email,
            phoneNumber = updatedUser.phoneNumber,
            bio = updatedUser.bio,
            profilePictureUrl = updatedUser.profilePictureUrl
        )

        insertUser(newUserProfile)
        return newUserProfile
    }
}
