package com.generosity.choobudo.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.generosity.choobudo.R
import com.generosity.choobudo.common.ViewModelFather
import com.generosity.choobudo.common.common
import com.generosity.choobudo.common.getStringInPreference
import com.generosity.choobudo.models.*
import com.generosity.choobudo.retrofit.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainScreenViewModel (application: Application) : ViewModelFather(application) {

    val generalRepo=Repository()
    var websiteResponse: MutableLiveData<List<WebsiteResponse>>?=MutableLiveData()
    var userOrders: MutableLiveData<List<WebsiteResponse>>?=MutableLiveData()
    var userResponse: MutableLiveData<UserResponse>?=MutableLiveData()
    var associationsResponse: MutableLiveData<List<AssociationsResponse>>?=MutableLiveData()
    var sortedWebsite:MutableLiveData<List<WebsiteResponse>>?=MutableLiveData()
    var opportunitiesResponse: MutableLiveData<OpportunitiesResponse> = MutableLiveData()
    var opportunities: MutableLiveData<List<Opportunity>>?=MutableLiveData()

    init {
    }


    /**
     * get website by name
     */
    fun getWebsiteByName(name: String) {
        val sortedWebsit1:ArrayList<WebsiteResponse> = ArrayList()
        for(s1 in websiteResponse?.value!!){
            if(s1.name == name) {
                sortedWebsit1.add(s1)
            }
        }
        sortedWebsite?.value=sortedWebsit1
    }


    /**
     * get opportunities
     */
    fun getOpportunities() {

        this.viewModelScope.launch {
            try {

                val callBackGetOpportunities: Callback<OpportunitiesResponse> =

                    object : Callback<OpportunitiesResponse> {

                        override fun onResponse(
                            call: Call<OpportunitiesResponse>,
                            response: Response<OpportunitiesResponse>
                        ) {
                            opportunitiesResponse.value=response.body()
                            opportunities?.value = opportunitiesResponse.value?.items
                            when(response.code()){
                                200 -> {

                                    isSuccess?.value=true
                                }
                                201 ->{
                                    isSuccess?.value=true
                                }
                                else->{
                                errorMsg?.value = response.code().toString()
                            }
                            }

                        }

                        override fun onFailure(
                            call: Call<OpportunitiesResponse>, t: Throwable
                        ) {
                            isSuccess?.value=false
                        }
                    }

                val cookie=getStringInPreference(getApplication<Application?>().applicationContext,
                    common.Constant.COOKIE_CONTENT,"-1")
                val token=getStringInPreference(getApplication<Application?>().applicationContext,
                    common.Constant.COOKIE_NAME,"-1")
                if(!cookie.equals("-1") && !token.equals("-1")) {
                    val opportunityResponse=generalRepo.getOpportunities(cookie, token)
                    opportunityResponse.enqueue(callBackGetOpportunities)
                }

            } catch (ex: Exception) {
                //loginResult?.value=BaseResponse.Error(ex.message)
            }
        }
    }



    //////////////


    /**
     * get associations
     */
    fun getAssociations() {

        this.viewModelScope.launch {
            try {

                val callBackGetAssociations: Callback<List<AssociationsResponse>?> =

                    object : Callback<List<AssociationsResponse>?> {

                        override fun onResponse(
                            call: Call<List<AssociationsResponse>?>,
                            response: Response<List<AssociationsResponse>?>
                        ) {
                            associationsResponse?.value=response.body()
                            when(response.code()){
                                200 -> {
                                    isSuccess?.value=true
                                }
                                201 ->{
                                    isSuccess?.value=true
                                }
                                403->{
                                    errorMsg?.value=getApplication<Application?>().applicationContext.resources.getString(
                                        R.string.email_pass_error)
                                }else->{
                                errorMsg?.value = response.code().toString()
                            }
                            }
                        }

                        override fun onFailure(
                            call: Call<List<AssociationsResponse>?>, t: Throwable
                        ) {
                            isSuccess?.value=false
                        }
                    }

                val associationResponse=generalRepo.getAssociations()
                associationResponse?.enqueue(callBackGetAssociations)

            } catch (ex: Exception) {
                //loginResult?.value=BaseResponse.Error(ex.message)
            }
        }
    }


    fun getWebSite(isGetUser: Boolean) {

            this.viewModelScope.launch {
                try {

                    val callBackGetAssociations: Callback<List<WebsiteResponse>?> =

                        object : Callback<List<WebsiteResponse>?> {

                            override fun onResponse(
                                call: Call<List<WebsiteResponse>?>,
                                response: Response<List<WebsiteResponse>?>
                            ) {
                                websiteResponse?.value=response.body()
                                when(response.code()){
                                    200 -> {
                                        if(isGetUser) {
                                            getUser()
                                        }
                                        isSuccess?.value=true
                                    }
                                    201 ->{
                                        if(isGetUser) {
                                            getUser()
                                        }
                                        isSuccess?.value=true
                                    }
                                    403->{
                                        errorMsg?.value=getApplication<Application?>().applicationContext.resources.getString(
                                            R.string.email_pass_error)
                                    }else->{
                                         errorMsg?.value = response.code().toString()
                                    }
                                }

                            }

                            override fun onFailure(
                                call: Call<List<WebsiteResponse>?>, t: Throwable
                            ) {
                                isSuccess?.value=false
                            }
                        }

                    val cookie=getStringInPreference(getApplication<Application?>().applicationContext,
                        common.Constant.COOKIE_CONTENT,"-1")
                    val token=getStringInPreference(getApplication<Application?>().applicationContext,
                        common.Constant.COOKIE_NAME,"-1")
                    if(!cookie.equals("-1") && !token.equals("-1")) {
                        val associationResponse=generalRepo.getWebsite(cookie, token)
                        associationResponse?.enqueue(callBackGetAssociations)
                    }

                } catch (ex: Exception) {
                    //loginResult?.value=BaseResponse.Error(ex.message)
                }
            }
        }

    /**
     * get current user details
     */
    fun getUser() {

        this.viewModelScope.launch {
            try {

                val callBackGetUser: Callback<UserResponse> =

                    object : Callback<UserResponse> {

                        override fun onResponse(
                            call: Call<UserResponse>,
                            response: Response<UserResponse>
                        ) {

                            userResponse?.value=response.body()

                            when(response.code()){
                                200-> {
                                    getOrderUserById()
                                    isSuccess?.value=true
                                }
                                201->{
                                    getOrderUserById()
                                    isSuccess?.value=true
                                }
                                403->{
                                        errorMsg?.value=getApplication<Application?>().applicationContext.resources.getString(
                                           R.string.email_pass_error)
                                    }else->{
                                        errorMsg?.value = response.code().toString()
                                }
                            }
                        }

                        override fun onFailure(
                            call: Call<UserResponse>, t: Throwable
                        ) {
                            isSuccess?.value=false
                        }
                    }

                val cookie=getStringInPreference(getApplication<Application?>().applicationContext,
                    common.Constant.COOKIE_CONTENT,"-1")
                val token=getStringInPreference(getApplication<Application?>().applicationContext,
                    common.Constant.COOKIE_NAME,"-1")
                if(!cookie.equals("-1") && !token.equals("-1")) {
                    val getUserResponse=generalRepo.getUser(cookie, token)
                    getUserResponse?.enqueue(callBackGetUser)
                }

            } catch (ex: Exception) {
                //loginResult?.value=BaseResponse.Error(ex.message)
            }
        }
    }

    /**
     * get orders of user
     */
    private fun getOrderUserById() {
       if(websiteResponse!=null
           && userResponse!=null
           && userResponse?.value?.orders!=null) {
           val uo = ArrayList<WebsiteResponse>()
           for (order in userResponse?.value?.orders!!) {
               for (website in websiteResponse?.value!!) {
                   if(order.website_id == website.id){
                       uo.add(website)
                   }
               }
           }
           userOrders?.value = uo
       }
    }
}