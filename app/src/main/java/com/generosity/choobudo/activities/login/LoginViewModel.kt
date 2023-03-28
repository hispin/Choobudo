package com.generosity.choobudo.activities.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.generosity.choobudo.models.LoginRequest
import com.generosity.choobudo.models.LoginResponse
import com.generosity.choobudo.retrofit.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var loginRequest: LoginRequest?=null
    val userRepo=UserRepository()
    var loginResponse: MutableLiveData<LoginResponse>?=MutableLiveData()
    var isSuccess: MutableLiveData<Boolean>?=null

    init {
        isSuccess=MutableLiveData(false)
    }

    /**
     * do login
     */
    fun loginUser() {

        this.viewModelScope.launch {
            try {

                val callBackLogin: Callback<LoginResponse?> =

                    object : Callback<LoginResponse?> {

                        override fun onResponse(
                            call: Call<LoginResponse?>, response: Response<LoginResponse?>
                        ) {
                            loginResponse?.value= response.body()
                            isSuccess?.value=true
                        }

                        override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                            isSuccess?.value=false
                        }
                    }

                if(loginRequest!=null) {
                    val loginResponse=userRepo.loginUser(loginRequest!!)
                    loginResponse?.enqueue(callBackLogin)
                }

            } catch (ex: Exception) {
                //loginResult?.value=BaseResponse.Error(ex.message)
            }
        }
    }

    /**
     * fill user details
     */
    fun setUser(email: String, pwd: String){
        loginRequest=LoginRequest(email, pwd)
    }


}