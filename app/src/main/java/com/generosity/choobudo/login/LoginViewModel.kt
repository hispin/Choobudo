package com.generosity.choobudo.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.generosity.choobudo.R
import com.generosity.choobudo.common.ViewModelFather
import com.generosity.choobudo.models.LoginRequest
import com.generosity.choobudo.models.LoginResponse
import com.generosity.choobudo.retrofit.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel(application: Application) : ViewModelFather(application) {

    var loginRequest: LoginRequest?=null
    val userRepo=Repository()
    var loginResponse: MutableLiveData<LoginResponse>?=MutableLiveData()

    init {
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

                            when(response.code()){
                                200-> {
                                    if(loginResponse?.value?.token_key!=null) {
                                        setSuccessResponse(
                                            response.headers()["Set-Cookie"], loginResponse?.value?.token_key!!
                                        )
                                    }
                                }
                                201-> {
                                    if(loginResponse?.value?.token_key!=null) {
                                        setSuccessResponse(
                                            response.headers()["Set-Cookie"], loginResponse?.value?.token_key!!
                                        )
                                    }
                                }
                                403->{
                                    errorMsg?.value=getApplication<Application?>().applicationContext.resources.getString(
                                        R.string.email_pass_error)
                                }else->{
                                    errorMsg?.value = response.code().toString()
                                }
                            }
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