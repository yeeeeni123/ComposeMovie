package com.yeen.data.repository

import com.yeen.model.User

interface UserRepository {
    suspend fun insertUser(user: User)
    suspend fun getUserByEmail(email: String): User?
}