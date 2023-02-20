package com.generosity.choobudo.retrofit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.generosity.choobudo.models.LoginRequest
import com.generosity.choobudo.models.LoginResponse
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo=UserRepository()
    var loginResult: MutableLiveData<BaseResponse<LoginResponse>>?=null


    fun loginUser(email: String, pwd: String) {

        loginResult=MutableLiveData()

        loginResult?.value=BaseResponse.Loading()
        this.viewModelScope.launch {
            try {

                val loginRequest=LoginRequest(
                    password=pwd, email=email
                )

                val response=userRepo.loginUser(loginRequest=loginRequest)
                if (response?.code() == 200) {
                    loginResult?.value=BaseResponse.Success(response.body())
                } else {
                    loginResult?.value=BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                loginResult?.value=BaseResponse.Error(ex.message)
            }
        }
    }
}