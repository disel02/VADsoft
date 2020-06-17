package com.studio1hub.app.vadsoft.ui.user.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.studio1hub.app.vadsoft.data.repository.ArticleRepository
import com.studio1hub.app.vadsoft.data.repository.UserRepository

class UserDetailViewModel (private val userRepository: UserRepository) : ViewModel() {

    fun getUser(id: Int) = userRepository.getUserById(id).asLiveData()
}