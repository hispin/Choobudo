package com.generosity.choobudo.retrofit

import com.generosity.choobudo.models.LoginRequest
import com.generosity.choobudo.models.LoginResponse
import com.generosity.choobudo.models.RegistrationResponse
import com.generosity.choobudo.models.UserContributer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface Api {


    //@POST("/user/login")//@POST("/api/authaccount/login")
    //suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

//    @POST("/user/login")
    //fun loginUserJson(@Path("login") postfix: String?, @Body params: RequestBody?): Call<ResponseBody?>?


//    @POST("/user/login")
//    fun jsonLogin(@Body params: String): Call<LoginResponse>

    @POST("/user/login")
    fun userLogin(@Body params: LoginRequest): Call<LoginResponse>

    @POST("/user/register")
    fun userContributerReg(@Body params: UserContributer): Call<RegistrationResponse>

    companion object {
        fun getApi(): Api? {
            return ApiClient.ApiClient.client?.create(Api::class.java)
        }
    }

}