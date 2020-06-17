package com.studio1hub.app.vadsoft.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.studio1hub.app.vadsoft.data.local.user.UserDao
import com.studio1hub.app.vadsoft.data.remote.AppService
import com.studio1hub.app.vadsoft.data.repository.UserRepository
import com.studio1hub.app.vadsoft.ui.user.detail.UserDetailViewModel
import com.studio1hub.app.vadsoft.ui.user.list.UserListViewModel
import java.lang.IllegalArgumentException

class UserViewModelFactory (private val userDao: UserDao, private val appService: AppService): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java))
        {
            return UserListViewModel(
                UserRepository(userDao, appService)
            ) as T
        }
        else if (modelClass.isAssignableFrom(UserDetailViewModel::class.java))
        {
            return UserDetailViewModel(
                UserRepository(userDao, appService)
            ) as T
        }
        throw IllegalArgumentException("Unknown Class Name")
    }

}
