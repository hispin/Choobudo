package com.generosity.choobudo.retrofit

import com.generosity.choobudo.models.LoginRequest
import com.generosity.choobudo.models.LoginResponse
import com.generosity.choobudo.models.RegistrationResponse
import com.generosity.choobudo.models.UserContributer
import retrofit2.Call

class UserRepository {

    suspend fun loginUser(param: LoginRequest): Call<LoginResponse>? {
        //return Api.getApi()?.loginUser(loginRequest=loginRequest)
        return Api.getApi()?.userLogin(param)

    }

    suspend fun regUser(param: UserContributer): Call<RegistrationResponse>? {
        //return Api.getApi()?.loginUser(loginRequest=loginRequest)
        return Api.getApi()?.userContributerReg(param)

    }

}