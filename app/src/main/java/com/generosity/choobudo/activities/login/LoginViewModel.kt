package com.generosity.choobudo.activities.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.generosity.choobudo.models.LoginRequest
import com.generosity.choobudo.models.LoginResponse
import com.generosity.choobudo.retrofit.BaseResponse
import com.generosity.choobudo.retrofit.UserRepository
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel(application: Application) : AndroidViewModel(application) {


    val userRepo=UserRepository()
    var loginResult: MutableLiveData<BaseResponse<JsonObject>>?=null

    fun loginUser(email: String, pwd: String) {

        loginResult=MutableLiveData()

        loginResult?.value=BaseResponse.Loading()
        this.viewModelScope.launch {
            try {

                var callBackRegistraPagador: Callback<LoginResponse?> =

                    object : Callback<LoginResponse?> {

                        override fun onResponse(
                            call: Call<LoginResponse?>, response: Response<LoginResponse?>
                        ) {
                            val n=10
                        }

                        override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                            val n=10
                        }
                    }

                val loginResponse=userRepo.loginUser(LoginRequest(email, pwd))
                loginResponse?.enqueue(callBackRegistraPagador)

            } catch (ex: Exception) {
                loginResult?.value=BaseResponse.Error(ex.message)
            }
        }
    }


}