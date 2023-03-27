package com.generosity.choobudo.activities.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.generosity.choobudo.R
import com.generosity.choobudo.Session
import com.generosity.choobudo.databinding.ActivityMainBinding
import com.generosity.choobudo.models.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val token=Session.SessionManager.getToken(this)

        viewModel.isSuccess?.observe(this, Observer {
            if (it) {
                Toast.makeText(
                    this@LoginActivity,
                    resources.getString(R.string.success_msg),
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                Toast.makeText(
                    this@LoginActivity,
                    resources.getString(R.string.failed_msg),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
//        viewModel.loginResult?.observe(this) {
//            when (it) {
//                is BaseResponse.Loading -> {
//                    showLoading()
//                }
//
//                is BaseResponse.Success -> {
//                    stopLoading()
//                }
//
//                is BaseResponse.Error -> {
//                    processError(it.msg)
//                }
//                else -> {
//                    stopLoading()
//                }
//            }
//        }

        ivLogin.setOnClickListener {
            doLogin()
        }


    }


    /**
     * make login
     */
    fun doLogin() {
        val email=etUsername.text.toString()
            //"hag.hispin@gmail.com"//"arielmunk+6@gmail.com"//"App@API_user1!"//binding.txtInputEmail.text.toString()
        val pwd=etPassword.text.toString()//"1234"//resources.getString(R.string.password)//binding.txtPass.text.toString()
        viewModel.setUser(email, pwd)
        viewModel.loginUser()

    }

    //
//    fun doSignup() {
//
//    }
//
    fun showLoading() {
        binding.prgbar.visibility=View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility=View.GONE
    }

    fun processLogin(data: LoginResponse?) {
        showToast("Success:")
//            if (!data?.data?.token.isNullOrEmpty()) {
//                data?.data?.token?.let { Session.SessionManager.saveAuthToken(this, it) }
//                navigateToHome()
//            }
    }

    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}