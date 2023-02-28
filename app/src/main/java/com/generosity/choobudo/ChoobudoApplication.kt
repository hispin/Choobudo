package com.generosity.choobudo

import android.app.Application
import com.generosity.choobudo.activities.BaseActivity
import java.util.*


class ChoobudoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        BaseActivity.dLocale=Locale("he")
    }

}