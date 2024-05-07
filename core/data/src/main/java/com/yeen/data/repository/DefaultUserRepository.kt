package com.yeen.data.repository

import com.yeen.database.UserDao
import com.yeen.model.User
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }
}