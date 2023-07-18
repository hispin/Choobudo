package com.generosity.choobudo.main

import android.app.Application
import android.util.Log
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
    var userAssociationResponse:MutableLiveData<UserAssociationResponse>?=MutableLiveData()
    var associationsResponse: MutableLiveData<List<AssociationsResponse>>?=MutableLiveData()
    var sortedWebsite:MutableLiveData<List<WebsiteResponse>>?=MutableLiveData()
    var opportunitiesResponse: MutableLiveData<OpportunitiesResponse> = MutableLiveData()
    var opportunities: MutableLiveData<List<Opportunity>>?=MutableLiveData()
    var isEditable:MutableLiveData<Boolean> = MutableLiveData(false)

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
                                            getUser(true)
                                        }
                                        isSuccess?.value=true
                                    }
                                    201 ->{
                                        if(isGetUser) {
                                            getUser(true)
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
     * get current association user details
     */
    fun getAssociationUser() {

        this.viewModelScope.launch {
            try {

                val callBackGetUser: Callback<UserAssociationResponse> =

                    object : Callback<UserAssociationResponse> {

                        override fun onResponse(
                            call: Call<UserAssociationResponse>,
                            response: Response<UserAssociationResponse>
                        ) {

                            when(response.code()){
                                200-> {
                                    userAssociationResponse?.value=response.body()
                                    isSuccess?.value=true
                                }
                                201->{
                                    userAssociationResponse?.value=response.body()
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
                            call: Call<UserAssociationResponse>, t: Throwable
                        ) {
                            isSuccess?.value=false
                        }
                    }

                val cookie=getStringInPreference(getApplication<Application?>().applicationContext,
                    common.Constant.COOKIE_CONTENT,"-1")
                val token=getStringInPreference(getApplication<Application?>().applicationContext,
                    common.Constant.COOKIE_NAME,"-1")
                if(!cookie.equals("-1") && !token.equals("-1")) {
                    val getUserResponse=generalRepo.getAssociationUser(cookie, token)
                    getUserResponse?.enqueue(callBackGetUser)
                }

            } catch (ex: Exception) {
                //loginResult?.value=BaseResponse.Error(ex.message)
            }
        }
    }

    /**
     * get current user details
     */
    fun getUser(isNeedOrders:Boolean) {

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
                                    if(isNeedOrders) {
                                        getOrderUserById()
                                    }
                                    isSuccess?.value=true
                                }
                                201->{
                                    if(isNeedOrders) {
                                        getOrderUserById()
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

    /**
     * change edit/read only status
     */
    fun setEditable(status: Boolean) {
         isEditable.value=status
    }


    /**
     * update contributer
     *
     * */

    fun updateAssociation(userStatus: Int?) {
//         var userContributer = UserContributer("haggay","chen",1,1,1990
//             ,"8e8b988a-8af9-4383-a410-192c01f552a0","hag.swead15@gmail.com","1234","0546596387","haifa","israel",true,194,180)


        this.viewModelScope.launch {
            try {

                var callBackGetUser: Callback<UserAssociationResponse?> =

                    object : Callback<UserAssociationResponse?> {

                        override fun onResponse(
                            call: Call<UserAssociationResponse?>,
                            response: Response<UserAssociationResponse?>
                        ) {
                            Log.d("responseReg", "success")
                            //val cookie=response.headers()["Set-Cookie"]

                            //save the response
                            userAssociationResponse?.value=response.body()

                            when(response.code()){
                                200 -> {
//                                    if(registrationResponse?.value?.token_key!=null) {
//                                        setSuccessResponse(
//                                            cookie, registrationResponse?.value?.token_key!!
//                                        )
//                                    }
                                }
                                201->{
//                                    if(registrationResponse?.value?.token_key!=null) {
//                                        setSuccessResponse(
//                                            cookie, registrationResponse?.value?.token_key!!
//                                        )
//                                    }
                                }
                                403->{
                                    errorMsg?.value=getApplication<Application?>().applicationContext.resources.getString(
                                        R.string.email_pass_error)
                                }else->{
                                errorMsg?.value = response.code().toString()
                            }
                            }



                        }

                        override fun onFailure(call: Call<UserAssociationResponse?>, t: Throwable) {
                            isSuccess?.value=false
                            Log.d("responseReg", "failed")
                        }
                    }


                val cookie=getStringInPreference(getApplication<Application?>().applicationContext,
                    common.Constant.COOKIE_CONTENT,"-1")
                val token=getStringInPreference(getApplication<Application?>().applicationContext,
                    common.Constant.COOKIE_NAME,"-1")
                if(!cookie.equals("-1") && !token.equals("-1")) {
                    val userAssociation=UserAssociation()
                    userAssociation.user_type=userStatus
                    userAssociationResponse?.value?.let {
                        userAssociation.parseAssociationUser(it)
                        val reponse=generalRepo.updateAssociationUser(cookie, token, userAssociation)
                        reponse?.enqueue(callBackGetUser)
                    }
                }

            } catch (ex: Exception) {
                var n = 10
            }
        }

    }


    /**
     * update contributer
     *
     * */

    fun updateContributer(userStatus: Int?) {
//         var userContributer = UserContributer("haggay","chen",1,1,1990
//             ,"8e8b988a-8af9-4383-a410-192c01f552a0","hag.swead15@gmail.com","1234","0546596387","haifa","israel",true,194,180)


        this.viewModelScope.launch {
            try {

                var callBackGetUser: Callback<UserResponse?> =

                    object : Callback<UserResponse?> {

                        override fun onResponse(
                            call: Call<UserResponse?>,
                            response: Response<UserResponse?>
                        ) {
                            Log.d("responseReg", "success")
                            //val cookie=response.headers()["Set-Cookie"]

                            //save the response
                            userResponse?.value=response.body()
                            when(response.code()){
                                200 -> {
//                                    if(registrationResponse?.value?.token_key!=null) {
//                                        setSuccessResponse(
//                                            cookie, registrationResponse?.value?.token_key!!
//                                        )
//                                    }
                                }
                                201->{
//                                    if(registrationResponse?.value?.token_key!=null) {
//                                        setSuccessResponse(
//                                            cookie, registrationResponse?.value?.token_key!!
//                                        )
//                                    }
                                }
                                403->{
                                    errorMsg?.value=getApplication<Application?>().applicationContext.resources.getString(
                                        R.string.email_pass_error)
                                }else->{
                                errorMsg?.value = response.code().toString()
                            }
                            }



                        }

                        override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                            isSuccess?.value=false
                            Log.d("responseReg", "failed")
                        }
                    }


                val cookie=getStringInPreference(getApplication<Application?>().applicationContext,
                    common.Constant.COOKIE_CONTENT,"-1")
                val token=getStringInPreference(getApplication<Application?>().applicationContext,
                    common.Constant.COOKIE_NAME,"-1")
                if(!cookie.equals("-1") && !token.equals("-1")) {
                    val userContributer=UserContributer()
                    userContributer.user_type=userStatus
                    userResponse?.value?.let {
                        userContributer.parseUser(it)
                        val reponse=generalRepo.updateUser(cookie, token, userContributer)
                        reponse?.enqueue(callBackGetUser)
                    }
                }

            } catch (ex: Exception) {
                //registerResult?.value=BaseResponse.Error(ex.message)
            }
        }

    }
}