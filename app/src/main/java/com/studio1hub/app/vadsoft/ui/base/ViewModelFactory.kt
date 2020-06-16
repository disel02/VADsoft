package com.studio1hub.app.vadsoft.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.studio1hub.app.vadsoft.data.local.UserDao
import com.studio1hub.app.vadsoft.data.remote.UserService
import com.studio1hub.app.vadsoft.data.repository.MainRepository
import com.studio1hub.app.vadsoft.ui.main.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory (private val userDao: UserDao, private val userService: UserService): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
        {
            return MainViewModel(MainRepository(userDao,userService)) as T
        }
        throw IllegalArgumentException("Unknown Class Name")
    }

}
