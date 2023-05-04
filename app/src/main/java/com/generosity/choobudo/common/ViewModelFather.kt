package com.generosity.choobudo.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

open class ViewModelFather (application: Application) : AndroidViewModel(application) {

    var isSuccess: MutableLiveData<Boolean>?=null
    var errorMsg: MutableLiveData<String>?=null

    init {
        isSuccess=MutableLiveData(false)
        errorMsg = MutableLiveData("")
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
        setStringInPreference(getApplication<Application?>().applicationContext,
            common.Constant.COOKIE_CONTENT,cookie)

        //save the response
        //registrationResponse?.value=response.body()
        setStringInPreference(getApplication<Application?>().applicationContext,
            common.Constant.COOKIE_NAME,tokenKey)

        isSuccess?.value=true
    }

}