package com.createfuture.coachingservice.service

import com.createfuture.coachingservice.model.UserProfile
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserProfileService {

    // TODO: This should interact with persistence, somewhere
    private var userCache = mutableMapOf<UUID, UserProfile>()

}
