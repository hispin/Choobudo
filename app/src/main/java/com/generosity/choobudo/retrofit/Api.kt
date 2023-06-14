package com.generosity.choobudo.retrofit

import com.generosity.choobudo.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface Api {

    @GET("/user/")
    fun getUser(): Call<UserResponse>

    @GET("/websites/")
    fun getWebsites(): Call<List<WebsiteResponse>>

    @GET("/lists/opportunities/")
    fun getOpportunities(): Call<OpportunitiesResponse>

    @GET("/associations/")
    fun getAssociations(): Call<List<AssociationsResponse>>


    @POST("/user/login")
    fun userLogin(@Body params: LoginRequest): Call<LoginResponse>

    @POST("/user/register")
    fun userContributerReg(@Body params: UserContributer): Call<RegistrationResponse>

    @POST("/user/register")
    fun userAssociationReg(@Body params: UserAssociation): Call<RegistrationAssociationResponse>

    companion object {
        fun getApi(): Api? {
            return ApiClient.ApiClient.client?.create(Api::class.java)
        }

        fun getApiWithCookie(cookie: String?, token: String?): Api? {
            if(token==null || cookie ==null)
                   return null
            ApiClient.Constant.token=token
            ApiClient.Constant.cookie=cookie
            return ApiClient.ApiClientWithCookie.client?.create(Api::class.java)
        }
    }

}