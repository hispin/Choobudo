package com.generosity.choobudo.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.generosity.choobudo.common.common
import com.generosity.choobudo.common.getStringInPreference
import com.generosity.choobudo.models.RegistrationResponse
import com.generosity.choobudo.models.WebsiteResponse
import com.generosity.choobudo.retrofit.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainScreenViewModel (application: Application) : AndroidViewModel(application) {

    val websiteRepo=Repository()
    var websiteResponse: MutableLiveData<List<WebsiteResponse>>?=MutableLiveData()
    var isSuccess: MutableLiveData<Boolean>?=null
    var userResponse: MutableLiveData<RegistrationResponse>?=MutableLiveData()

    init {
        isSuccess=MutableLiveData(false)
    }

    fun getWebSite() {

            this.viewModelScope.launch {
                try {

                    val callBackGetAssociations: Callback<List<WebsiteResponse>?> =

                        object : Callback<List<WebsiteResponse>?> {

                            override fun onResponse(
                                call: Call<List<WebsiteResponse>?>,
                                response: Response<List<WebsiteResponse>?>
                            ) {
                                isSuccess?.value=true
                                websiteResponse?.value=response.body()
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
                        val associationResponse=websiteRepo.getWebsite(cookie, token)
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

                val callBackGetUser: Callback<RegistrationResponse> =

                    object : Callback<RegistrationResponse> {

                        override fun onResponse(
                            call: Call<RegistrationResponse>,
                            response: Response<RegistrationResponse>
                        ) {
                            isSuccess?.value=true
                            userResponse?.value=response.body()
                        }

                        override fun onFailure(
                            call: Call<RegistrationResponse>, t: Throwable
                        ) {
                            isSuccess?.value=false
                        }
                    }

                val cookie=getStringInPreference(getApplication<Application?>().applicationContext,
                    common.Constant.COOKIE_CONTENT,"-1")
                val token=getStringInPreference(getApplication<Application?>().applicationContext,
                    common.Constant.COOKIE_NAME,"-1")
                if(!cookie.equals("-1") && !token.equals("-1")) {
                    val getUserResponse=websiteRepo.getUser(cookie, token)
                    getUserResponse?.enqueue(callBackGetUser)
                }

            } catch (ex: Exception) {
                //loginResult?.value=BaseResponse.Error(ex.message)
            }
        }
    }
}