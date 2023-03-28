package com.generosity.choobudo.activities.registration

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.generosity.choobudo.common.common.Constant.COOKIE_CONTENT
import com.generosity.choobudo.common.common.Constant.COOKIE_NAME
import com.generosity.choobudo.common.setStringInPreference
import com.generosity.choobudo.models.AssociationsResponse
import com.generosity.choobudo.models.RegistrationResponse
import com.generosity.choobudo.models.UserContributer
import com.generosity.choobudo.retrofit.BaseResponse
import com.generosity.choobudo.retrofit.UserRepository
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    //{ "email": "hag.hispin@gmail.com", "password": 1234, "first_name": "אריאל ", "last_name": " בדיקת APP API", "phone": "054-7557804", "city": " ירושלי ם ", "state": " ישראל ", "birth_day": 1, "birth_month": 1, "birth_year": 1970, "organization_guid": "8e8b988a-8af9-4383-a410-192c01f552a0", "term_accepted": true, "group_donation": 194, "user_type": 180 }
    var userContributer: UserContributer?=null
    val userRepo=UserRepository()
    var registerResult: MutableLiveData<BaseResponse<JsonObject>>?=null
    var isSuccess: MutableLiveData<Boolean>?=null
    var registrationResponse: MutableLiveData<RegistrationResponse>?=MutableLiveData()
    var associationsResponse: MutableLiveData<List<AssociationsResponse>>?=MutableLiveData()

    init {
        userContributer=UserContributer()
        isSuccess=MutableLiveData(false)
    }

    fun register() {
//         var userContributer = UserContributer("haggay","chen",1,1,1990
//             ,"8e8b988a-8af9-4383-a410-192c01f552a0","hag.swead15@gmail.com","1234","0546596387","haifa","israel",true,194,180)

        registerResult=MutableLiveData()

        registerResult?.value=BaseResponse.Loading()
        this.viewModelScope.launch {
            try {

                var callBackRegistraPagador: Callback<RegistrationResponse?> =

                    object : Callback<RegistrationResponse?> {

                        override fun onResponse(
                            call: Call<RegistrationResponse?>,
                            response: Response<RegistrationResponse?>
                        ) {
                            isSuccess?.value=true
                            Log.d("responseReg", "success")
                            val cookie=response.headers()["Set-Cookie"]
                            //save the content of cookie in share preference
                            setStringInPreference(getApplication<Application?>().applicationContext,COOKIE_CONTENT,cookie)

                            //save the response
                            registrationResponse?.value=response.body()
                            setStringInPreference(getApplication<Application?>().applicationContext,COOKIE_NAME,registrationResponse?.value?.token_key)

                        }

                        override fun onFailure(call: Call<RegistrationResponse?>, t: Throwable) {
                            isSuccess?.value=false
                            Log.d("responseReg", "failed")
                        }
                    }


                val regResponse=userContributer?.let { userRepo.regUser(it) }
                regResponse?.enqueue(callBackRegistraPagador)

            } catch (ex: Exception) {
                registerResult?.value=BaseResponse.Error(ex.message)
            }
        }

    }


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
                            isSuccess?.value=true
                            associationsResponse?.value=response.body()
                        }

                        override fun onFailure(
                            call: Call<List<AssociationsResponse>?>, t: Throwable
                        ) {
                            isSuccess?.value=false
                        }
                    }

                val associationResponse=userRepo.getAssociations()
                associationResponse?.enqueue(callBackGetAssociations)

            } catch (ex: Exception) {
                //loginResult?.value=BaseResponse.Error(ex.message)
            }
        }
    }


    /**
     * set stage 1 of registration contributer
     */
    fun setContributerStage1(pName: String, fName: String, associationType: String?) {
        userContributer?.first_name=pName
        userContributer?.last_name=fName
        userContributer?.organization_guid=associationType
    }

    /**
     * set stage 2 of registration contributer
     */
    fun setContributerStage2(tel: String, email: String, city: String, country: String) {
        userContributer?.phone=tel
        userContributer?.email=email
        userContributer?.city=city
        userContributer?.state=country
    }

    /**
     * set stage 3 of registration contributer
     */
    fun setContributerStage3(
        day: String, month: String, year: String, password: String, checked: Boolean
    ) {
        userContributer?.birth_day=day.toInt()
        userContributer?.birth_month=month.toInt()
        userContributer?.birth_year=year.toInt()
        userContributer?.password=password
        userContributer?.term_accepted=checked
        userContributer?.group_donations=194
        userContributer?.user_type=180
    }


}