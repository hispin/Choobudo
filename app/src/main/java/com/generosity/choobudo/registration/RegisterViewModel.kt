package com.generosity.choobudo.registration

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.generosity.choobudo.common.common.Constant.COOKIE_CONTENT
import com.generosity.choobudo.common.common.Constant.COOKIE_NAME
import com.generosity.choobudo.common.setStringInPreference
import com.generosity.choobudo.models.*
import com.generosity.choobudo.retrofit.BaseResponse
import com.generosity.choobudo.retrofit.Repository
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    //{ "email": "hag.hispin@gmail.com", "password": 1234, "first_name": "אריאל ", "last_name": " בדיקת APP API", "phone": "054-7557804", "city": " ירושלי ם ", "state": " ישראל ", "birth_day": 1, "birth_month": 1, "birth_year": 1970, "organization_guid": "8e8b988a-8af9-4383-a410-192c01f552a0", "term_accepted": true, "group_donation": 194, "user_type": 180 }
    var userContributer: UserContributer?=null
    var userAssociation: UserAssociation?=null
    val userRepo=Repository()
    var registerResult: MutableLiveData<BaseResponse<JsonObject>>?=null
    var isSuccess: MutableLiveData<Boolean>?=null
    var registrationResponse: MutableLiveData<RegistrationResponse>?=MutableLiveData()
    var registrationAssociationResponse: MutableLiveData<RegistrationAssociationResponse>?=MutableLiveData()
    var associationsResponse: MutableLiveData<List<AssociationsResponse>>?=MutableLiveData()
    var isRegFirstValidate: MutableLiveData<Boolean>?=null


    init {
        userContributer=UserContributer()
        userAssociation=UserAssociation()
        isSuccess=MutableLiveData(false)
        isRegFirstValidate=MutableLiveData(false)
    }

    fun registerContributer() {
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


                val regResponse=userContributer?.let { userRepo.regContributerUser(it) }
                regResponse?.enqueue(callBackRegistraPagador)

            } catch (ex: Exception) {
                registerResult?.value=BaseResponse.Error(ex.message)
            }
        }

    }

    fun registerAssociation() {
//       val userAssociation1 = UserAssociation()
//        userAssociation1.email = "hag.hispin100@gmail.com"
//        userAssociation1.password = "1234"
//        userAssociation1.phone = "0536596381"
//        userAssociation1.city = "נתניה"
//        userAssociation1.state = "ישראל"
//        userAssociation1.term_accepted = true
//        userAssociation1.group_donations = 194
//        userAssociation1.organization_type = 186
//        userAssociation1.organization_name="בדיקת עמותה"
//        userAssociation1.have_section_46 = false
//        userAssociation1.contact_first_name="חגי"
//        userAssociation1.contact_last_name="כהן"
//        userAssociation1.contact_position="מזכיר"
//        userAssociation1.street="ראש פינה"
//        userAssociation1.home_num="3"
//        userAssociation1.postal_code="12345"
//        userAssociation1.bank_account_name="חשבון של חגי"
//        userAssociation1.bank_account_num=23
//        userAssociation1.bank_branch_name="סניף"
//        userAssociation1.bank_branch_num = 12
//        userAssociation1.update_in_mail=false
//        userAssociation1.update_in_sms=false
//        userAssociation1.update_in_whatsapp=false
//        userAssociation1.organization_summary="טקס גיבור המערב הפרוע"
//        userAssociation1.website_link="www.ynet.co.il"
//        userAssociation1.group_donation_abroad_account_in_jgive =false
//        userAssociation1.group_donation_abroad_uk_tormet = false
//        userAssociation1.user_type = 181

        registerResult=MutableLiveData()

        registerResult?.value=BaseResponse.Loading()
        this.viewModelScope.launch {
            try {

                var callBackRegistraPagador: Callback<RegistrationAssociationResponse?> =

                    object : Callback<RegistrationAssociationResponse?> {

                        override fun onResponse(
                            call: Call<RegistrationAssociationResponse?>,
                            response: Response<RegistrationAssociationResponse?>
                        ) {
                            isSuccess?.value=true
                            Log.d("responseReg", "success")
                            val cookie=response.headers()["Set-Cookie"]
                            //save the content of cookie in share preference
                            setStringInPreference(getApplication<Application?>().applicationContext,COOKIE_CONTENT,cookie)

                            //save the response
                            registrationAssociationResponse?.value=response.body()
                            setStringInPreference(getApplication<Application?>().applicationContext,COOKIE_NAME,registrationResponse?.value?.token_key)

                        }

                        override fun onFailure(call: Call<RegistrationAssociationResponse?>, t: Throwable) {
                            isSuccess?.value=false
                            Log.d("responseReg", "failed")
                        }
                    }


                val regResponse=userAssociation?.let { userRepo.regAssociationUser(it) }
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

    /**
     * set stage 1 of association registration
     */
    fun setAssociationStage1(organizationType: Int?, associationName: String, haveSection46: Boolean?, contactPosition: String) {
        userAssociation?.organization_type = organizationType
        userAssociation?.organization_name = associationName
        userAssociation?.have_section_46 = haveSection46
        userAssociation?.contact_position = contactPosition
    }

    /**
     * set stage 2 of association registration
     */
    fun setAssociationStage2(privateName: String, familyName: String, mobile: String, email: String) {
        userAssociation?.contact_first_name = privateName
        userAssociation?.contact_last_name = familyName
        userAssociation?.phone = mobile
        userAssociation?.email = email
    }

    /**
     * set stage 3 of association registration
     */
    fun setAssociationStage3(associateAddress: String, numAddress: String, city: String, country: String, postal: String) {
        userAssociation?.street = associateAddress
        userAssociation?.home_num = numAddress
        userAssociation?.city = city
        userAssociation?.state = country
        userAssociation?.postal_code = postal
    }

    /**
     * set stage 4 of association registration
     */
    fun setAssociationStage4(accounName: String, accountNum: String, branchName: String, branchNum: String, bankNum: String) {
        userAssociation?.bank_account_name = accounName
        userAssociation?.bank_account_num = accountNum.toIntOrNull()
        userAssociation?.bank_branch_name = branchName
        userAssociation?.bank_branch_num = branchNum.toIntOrNull()
        userAssociation?.bank_num = bankNum.toIntOrNull()
    }

    /**
     * set stage 5 of association registration
     */
    fun setAssociationStage6(password: String, personalAssociationLink: String, iconFirm: Boolean?, iRead: Boolean?) {
        userAssociation?.password = password
        userAssociation?.website_link = personalAssociationLink
        userAssociation?.update_in_mail = iconFirm
        userAssociation?.update_in_sms = iconFirm
        userAssociation?.update_in_whatsapp = iconFirm
        userAssociation?.term_accepted =iRead
        userAssociation?.group_donations=194
        userAssociation?.user_type=181
        userAssociation?.group_donation_abroad_account_in_jgive =false
        userAssociation?.group_donation_abroad_uk_tormet = false
        userAssociation?.organization_summary = "טקס גיבור המערב הפרוע"
    }

    fun setRegFirstValidate(validate: Boolean) {
        isRegFirstValidate?.value=validate
    }


}