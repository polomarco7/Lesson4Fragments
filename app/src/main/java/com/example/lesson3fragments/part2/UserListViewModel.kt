package com.example.lesson3fragments.part2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {
    val repository = Repository()
    private val _users = MutableStateFlow<List<Users>>(emptyList())
    val users: StateFlow<List<Users>> = _users.asStateFlow()

    init {
        getContactsData()
    }

    private fun getContactsData() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.userList
            }.fold(
                onSuccess = { _users.value = it },
                onFailure = { Log.d("ViewModel", it.message ?: "") }
            )
        }
    }
}