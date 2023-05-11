package com.generosity.choobudo.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.generosity.choobudo.R
import com.generosity.choobudo.common.common
import com.generosity.choobudo.common.getStringInPreference
import com.generosity.choobudo.login.LoginActivity
import com.generosity.choobudo.login.LoginViewModel
import com.generosity.choobudo.registration.RegisterActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginViewModel>()
    private var email:String?=null
    private var password:String?=null
    private var splashProgressBar:ProgressBar?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
        setListener()
        if(isUserExist()){
            doLogin()
        }
    }

    private fun setListener() {
        viewModel.isSuccess?.observe(this, Observer {
            if (viewModel.isAction) {
                if (it) {
                    Toast.makeText(
                        this@SplashActivity,
                        resources.getString(R.string.success_msg),
                        Toast.LENGTH_SHORT
                    ).show()
                    common.openMainScreen(this)
                } else {
                    Toast.makeText(
                        this@SplashActivity,
                        resources.getString(R.string.failed_msg),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
    /**
     * check if the user has been log in previously
     */
    private fun isUserExist(): Boolean {
        email=getStringInPreference(this, common.Constant.USER_KEY,"-1")
        password=getStringInPreference(this, common.Constant.PASS_KEY,"-1")
        if(email.equals("-1") && password.equals("-1") ){
            return false
        }
        return true
    }

    private fun initView() {
        ivRegistration.setOnClickListener {
            startActivity(Intent(this@SplashActivity, RegisterActivity::class.java))
        }
        ivLogin.setOnClickListener {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        }
        splashProgressBar=findViewById(R.id.splashProgressBar)
    }

    override fun onDestroy() {
        super.onDestroy()
        splashProgressBar?.visibility=View.GONE
    }

    /**
     * make login
     */
    fun doLogin() {
        if(!email.equals("-1")
            && !password.equals("-1")) {
            splashProgressBar?.visibility = View.VISIBLE
            viewModel.setUser(email!!, password!!)
            viewModel.loginUser()
        }
    }

}