package com.createfuture.coachingservice.service

import com.createfuture.coachingservice.model.UserProfile
import org.springframework.stereotype.Service
import java.util.UUID.randomUUID

@Service
class UserSeedService {

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
}
