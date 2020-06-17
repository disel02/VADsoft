package com.studio1hub.app.vadsoft.data.repository

import androidx.annotation.MainThread
import com.studio1hub.app.vadsoft.data.local.user.UserDao
import com.studio1hub.app.vadsoft.data.remote.AppService
import com.studio1hub.app.vadsoft.model.User
import com.studio1hub.app.vadsoft.utils.State
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class UserRepository(val userDao: UserDao, val appService: AppService) {

    fun getUsersRepo(): Flow<State<List<User>>> {
        return object: NetworkBoundRepository<List<User>, List<User>>(){

            override suspend fun fetchFromRemote(): Response<List<User>> = appService.getUsers()

            override suspend fun saveRemoteData(response: List<User>) = userDao.insertUsers(response)

            override fun fetchFromLocal(): Flow<List<User>> = userDao.getAllUsers()

        }.asFlow().flowOn(IO)
    }

    @MainThread
    fun getUserById(userId: Int): Flow<User> = userDao.getUserById(userId)
}