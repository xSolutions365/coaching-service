package com.createfuture.coachingservice.service

import com.createfuture.coachingservice.model.UserProfile
import org.springframework.stereotype.Service
import java.util.UUID.randomUUID

@Service
class UserSeedService {

    private val names: List<String> by lazy {
        javaClass.classLoader.getResourceAsStream("names.txt")
            ?.bufferedReader()?.readLines() ?: listOf("Foo")
    }

    private val surnames: List<String> by lazy {
        javaClass.classLoader.getResourceAsStream("surnames.txt")
            ?.bufferedReader()?.readLines() ?: listOf("Bar")
    }

    fun getRandomName(): String {
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
}
