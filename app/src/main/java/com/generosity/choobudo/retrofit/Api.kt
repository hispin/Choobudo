package com.generosity.choobudo.retrofit

import com.generosity.choobudo.models.LoginRequest
import com.generosity.choobudo.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {


    @POST("/user/login")//@POST("/api/authaccount/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    companion object {
        fun getApi(): Api? {
            return ApiClient.ApiClient.client?.create(Api::class.java)
        }
    }

}