package com.generosity.choobudo.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.generosity.choobudo.common.common.Constant.RESULT_RESET_PASSWORD_NONE

open class ViewModelFather (application: Application) : AndroidViewModel(application) {

    var isSuccess: MutableLiveData<Boolean>?=null
    var errorMsg: MutableLiveData<String>?=null
    var resultRequest:MutableLiveData<Int>?=null

    init {
        isSuccess=MutableLiveData(false)
        errorMsg = MutableLiveData("")
        resultRequest = MutableLiveData(RESULT_RESET_PASSWORD_NONE)
    }

    /**
     * set success response
     */
    fun setSuccessResponse(
        cookie: String?,
        tokenKey: String
    ) {
        //val cookie=response.headers()["Set-Cookie"]
        //save the content of cookie in share preference
        setStringInPreference(
            context=getApplication<Application>().applicationContext,
            key=common.Constant.COOKIE_CONTENT,
            value=cookie
        )

        //save the response
        //registrationResponse?.value=response.body()
        setStringInPreference(getApplication<Application>().applicationContext,
            common.Constant.COOKIE_NAME,tokenKey)

        isSuccess?.value=true
    }

}