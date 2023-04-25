package com.generosity.choobudo.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.generosity.choobudo.R
import com.generosity.choobudo.login.LoginActivity
import com.generosity.choobudo.registration.RegisterActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initView()
    }

    private fun initView() {
        ivRegistration.setOnClickListener {
            startActivity(Intent(this@SplashActivity, RegisterActivity::class.java))
        }
        ivLogin.setOnClickListener {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        }
    }
}