package com.generosity.choobudo.activities.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.generosity.choobudo.R
import com.generosity.choobudo.Session
import com.generosity.choobudo.databinding.ActivityMainBinding
import com.generosity.choobudo.models.LoginResponse
import com.generosity.choobudo.retrofit.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<LoginResponse> {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        val token=Session.SessionManager.getToken(this)

        viewModel.loginResult?.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            doLogin()

        }


    }

    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
        TODO("Not yet implemented")
    }

    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
        TODO("Not yet implemented")
    }


    fun doLogin() {
        val email=
            "hag.hispin@gmail.com"//"arielmunk+6@gmail.com"//"App@API_user1!"//binding.txtInputEmail.text.toString()
        val pwd="1234"//resources.getString(R.string.password)//binding.txtPass.text.toString()
        viewModel.loginUser(email=email, pwd=pwd)

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