package com.studio1hub.app.vadsoft.data.repository

import androidx.annotation.MainThread
import com.studio1hub.app.vadsoft.data.local.UserDao
import com.studio1hub.app.vadsoft.data.remote.UserService
import com.studio1hub.app.vadsoft.model.User
import com.studio1hub.app.vadsoft.utils.State
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class MainRepository(val userDao: UserDao, val userService: UserService) {

    fun getUsersRepo(): Flow<State<List<User>>> {
        return object: NetworkBoundRepository<List<User>, List<User>>(){

            override suspend fun fetchFromRemote(): Response<List<User>> = userService.getUsers()

            override suspend fun saveRemoteData(response: List<User>) = userDao.insertUsers(response)

            override fun fetchFromLocal(): Flow<List<User>> = userDao.getAllUsers()

        }.asFlow().flowOn(IO)
    }

    @MainThread
    fun getPostById(userId: Int): Flow<User> = userDao.getUserById(userId)
}