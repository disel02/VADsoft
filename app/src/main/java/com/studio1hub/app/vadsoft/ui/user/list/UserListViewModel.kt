package com.studio1hub.app.vadsoft.ui.user.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studio1hub.app.vadsoft.data.repository.UserRepository
import com.studio1hub.app.vadsoft.model.User
import com.studio1hub.app.vadsoft.utils.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserListViewModel(private val userRepository: UserRepository): ViewModel(){

    private val _postLiveData = MutableLiveData<State<List<User>>>()

    val postLivaData: LiveData<State<List<User>>>
        get() = _postLiveData

    fun getPostsModel(){
        viewModelScope.launch {
            userRepository.getUsersRepo().collect {
                _postLiveData.value = it
            }
        }
    }
}
