package com.generosity.choobudo.retrofit

import com.generosity.choobudo.models.LoginRequest
import com.generosity.choobudo.models.LoginResponse
import retrofit2.Response

class UserRepository {

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return Api.getApi()?.loginUser(loginRequest=loginRequest)
    }

}