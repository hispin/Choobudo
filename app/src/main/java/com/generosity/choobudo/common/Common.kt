package com.generosity.choobudo.common

import android.content.Context
import android.content.Intent
import com.generosity.choobudo.login.LoginActivity
import com.generosity.choobudo.main.MainScreenActivity
import java.util.regex.Pattern

class common {
    object Constant {
        const val BASE_URL="http://app-api-test.ziponet.co.il"//"http://app-api-test.ziponet.co.il"
        const val COOKIE_CONTENT = "cookieContent"
        const val COOKIE_NAME = "cookieName"
        const val PASSWORD_VALIDATION ="^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]{4,20}\$"
        const val URL_KEY="urlKey"
        const val IS_SCROLL_DWN_KEY="isScrollDown"
        const val USER_KEY="userKey"
        const val PASS_KEY="passKey"
        const val EMAIL_ALREADY_EXISTS="EMAIL_ALREADY_EXISTS"
        const val IS_SORTED_KEY="isSortedKey"
        const val CURRENT_USER_KEY="currentUserKey"
        const val USER_CONTRIBUTER=180
        const val USER_ASSOCIATION=181
        const val IE_EDITABLE_KEY="isEditAbleKey"
    }

    companion object {
        /**
         * open main screen
         */
        fun openMainScreen(context:Context){
            context.startActivity(Intent(context, MainScreenActivity::class.java))
        }

        /**
         * open log in
         */
        fun openLogin(context:Context){
            context.startActivity(Intent(context, LoginActivity::class.java))
        }

        /**
         * validation by format
         */
        fun isValidation(pattern:Pattern,field:String): Boolean {
            val matcher = pattern.matcher(field)
            return matcher.matches()
        }
    }
}