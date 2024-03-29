package com.generosity.choobudo.retrofit

import com.generosity.choobudo.login.ResetUser
import com.generosity.choobudo.login.ResetUserResponse
import com.generosity.choobudo.models.AssociationsResponse
import com.generosity.choobudo.models.LoginRequest
import com.generosity.choobudo.models.LoginResponse
import com.generosity.choobudo.models.OpportunitiesResponse
import com.generosity.choobudo.models.RegistrationAssociationResponse
import com.generosity.choobudo.models.RegistrationResponse
import com.generosity.choobudo.models.UserAssociation
import com.generosity.choobudo.models.UserAssociationResponse
import com.generosity.choobudo.models.UserContributer
import com.generosity.choobudo.models.UserResponse
import com.generosity.choobudo.models.WebsiteResponse
import retrofit2.Call

class Repository {

    suspend fun getAssociationUser(cookie: String?, token: String?): Call<UserAssociationResponse>? {
        return Api.getApiWithCookie(cookie,token)?.getAssociationUser()
    }

    suspend fun getUser(cookie: String?, token: String?): Call<UserResponse>? {
        return Api.getApiWithCookie(cookie,token)?.getUser()
        //return Api.getApi()?.getUser()
    }

    suspend fun getOpportunities(cookie: String?, token: String?): Call<OpportunitiesResponse> {
        return Api.getApiWithCookie(cookie,token)?.getOpportunities()!!

    }

    suspend fun getWebsite(cookie: String?, token: String?): Call<List<WebsiteResponse>>? {
        return Api.getApiWithCookie(cookie,token)?.getWebsites()

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

    suspend fun updateUser(cookie: String?, token: String?, value: UserContributer): Call<UserResponse>? {
        return value.let { Api.getApiWithCookie(cookie,token)?.updateContributer(it) }
    }

    fun updateAssociationUser(cookie: String?, token: String?, userAssociation: UserAssociation): Call<UserAssociationResponse>? {
        return userAssociation.let { Api.getApiWithCookie(cookie,token)?.updateAssociation(it) }
    }

    fun resetPassword(param: ResetUser): Call<ResetUserResponse>? {
        return Api.getApi()?.resetPassword(param)
    }
}