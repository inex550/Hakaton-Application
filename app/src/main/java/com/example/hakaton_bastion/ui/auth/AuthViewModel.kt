package com.example.hakaton_bastion.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hakaton_bastion.models.request.PinCode
import com.example.hakaton_bastion.network.BastionApi
import kotlinx.coroutines.launch
import retrofit2.HttpException


class AuthViewModel: ViewModel() {

    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?> get() = _token

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    val loadingState = MutableLiveData<Boolean>()

    val pinCode = MutableLiveData<String>()

    fun signIn(pinCode: PinCode) {
        loadingState.value = true

        viewModelScope.launch {
            try {
                _token.value = BastionApi.retrofitService.signIn(pinCode).token
            } catch (e: HttpException) {
                _token.value = null
            } catch (e: Exception) {
                _error.value = e.message
                e.printStackTrace()
            }

            loadingState.value = false
        }
    }
}