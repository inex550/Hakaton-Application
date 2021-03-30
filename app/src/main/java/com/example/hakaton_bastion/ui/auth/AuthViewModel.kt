package com.example.hakaton_bastion.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hakaton_bastion.Repository
import com.example.hakaton_bastion.models.network.User
import kotlinx.coroutines.launch
import retrofit2.HttpException


class AuthViewModel: ViewModel() {

    val user = MutableLiveData<User?>()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    val loadingState = MutableLiveData<Boolean>()

    val pinCode = MutableLiveData<String>()

    fun signIn(pinCode: String) {
        loadingState.value = true

        viewModelScope.launch {
            try {
                val loginUser = Repository.users.firstOrNull { pinCode == it.pinCode }
                user.value = loginUser

                if (loginUser != null)
                    Repository.currentUser = loginUser
            } catch (e: HttpException) {
                user.value = null
                e.printStackTrace()
            } catch (e: Exception) {
                _error.value = e.message
                e.printStackTrace()
            }

            loadingState.value = false
        }
    }
}