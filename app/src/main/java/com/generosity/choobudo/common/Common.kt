package com.generosity.choobudo.common

import android.content.Context
import android.content.Intent
import com.generosity.choobudo.activities.login.LoginActivity
import com.generosity.choobudo.activities.main.MainScreenActivity

class common {
    object Constant {
        const val BASE_URL="http://app-api-test.ziponet.co.il"//"http://app-api-test.ziponet.co.il"
        const val COOKIE_CONTENT = "cookieContent"
        const val COOKIE_NAME = "cookieName"
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
    }
}