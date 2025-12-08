package com.createfuture.coachingservice.service

import com.createfuture.coachingservice.model.UserProfile
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.UUID.randomUUID

@Service
class UserProfileService {

    // TODO: This should interact with persistence, somewhere
    private var userCache = mutableMapOf<UUID, UserProfile>()
    private val pageSize = 10;

    fun deleteUser(userId: UUID) = userCache.remove(userId)

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

    /* SEED UTILS */

    fun getRandomName(): String {
        val namesStream = javaClass.classLoader.getResourceAsStream("names.txt")
        val names = namesStream?.bufferedReader()?.readLines() ?: listOf("Random User")

        val surnamesStream = javaClass.classLoader.getResourceAsStream("surnames.txt")
        val surnames = surnamesStream?.bufferedReader()?.readLines() ?: listOf("Random User")

        return "${names.random()} ${surnames.random()}"
    }


    fun getRandomProfile(): UserProfile {
        val name = getRandomName()
        val username = name.lowercase().replace(" ", ".")

        return UserProfile(
            id = randomUUID(),
            username = username,
            preferredName = name,
            slackUsername = "@$username",
            email = "$username@createfuture.com"
        )
    }

    fun seed(count: Int): List<UUID> {

        val tempUserCache = mutableMapOf<UUID, UserProfile>()

        for (i in 1..count) {
            val user = getRandomProfile()
            tempUserCache[user.id] = user
        }

        userCache.putAll(tempUserCache)
        return tempUserCache.keys.toList()
    }

    /* END OF SEED UTILS */
}
