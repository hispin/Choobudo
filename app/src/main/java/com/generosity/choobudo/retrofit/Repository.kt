package com.generosity.choobudo.retrofit

import com.generosity.choobudo.models.*
import retrofit2.Call

class Repository {


    suspend fun getWebsite(cookie: String?, token: String?): Call<List<WebsiteResponse>>? {
        return Api.getApiWithCookie(token,cookie)?.getWebsites()

    }

    suspend fun getAssociations(): Call<List<AssociationsResponse>>? {
        return Api.getApi()?.getAssociations()

    }

    suspend fun loginUser(param: LoginRequest): Call<LoginResponse>? {
        //return Api.getApi()?.loginUser(loginRequest=loginRequest)
        return Api.getApi()?.userLogin(param)

    }

    /**
     * contributer registration
     */
    suspend fun regContributerUser(param: UserContributer): Call<RegistrationResponse>? {
        //return Api.getApi()?.loginUser(loginRequest=loginRequest)
        return Api.getApi()?.userContributerReg(param)

    }

    /**
     * association registration
     */
    suspend fun regAssociationUser(param: UserAssociation): Call<RegistrationAssociationResponse>? {
        //return Api.getApi()?.loginUser(loginRequest=loginRequest)
        return Api.getApi()?.userAssociationReg(param)

    }
}