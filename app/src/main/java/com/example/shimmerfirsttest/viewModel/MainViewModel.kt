package com.example.shimmerfirsttest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shimmerfirsttest.model.Resource
import com.example.shimmerfirsttest.model.User
import com.example.shimmerfirsttest.utils.UserDataProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val userListObserver = MutableLiveData<Resource<List<User>>>()

    fun getUserList(){
        userListObserver.value = Resource.Loading()

        viewModelScope.launch {
            delay(6000)
            userListObserver.value = Resource.Success(UserDataProvider.getAllUsers())
        }
    }
}