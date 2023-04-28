package com.generosity.choobudo.login

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.generosity.choobudo.R
import com.generosity.choobudo.activities.BaseActivity
import com.generosity.choobudo.common.common.Companion.openMainScreen

class LoginActivity : BaseActivity() {

    //private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<LoginViewModel>()
    private var isAction=false
    private var etUsername:EditText?=null
    private var etPassword:EditText?=null
    private var ivLogin: ImageView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        ivLogin = findViewById(R.id.ivLogin)
        ivLogin?.isEnabled=false
        ivLogin?.setOnClickListener {
            doLogin()
        }
        etUsername?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }

        etPassword?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }

        etPassword?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                validationFields()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        })


        viewModel.isSuccess?.observe(this, Observer {

            if (isAction) {
                if (it) {
                    Toast.makeText(
                        this@LoginActivity,
                        resources.getString(R.string.success_msg),
                        Toast.LENGTH_SHORT
                    ).show()
                    openMainScreen(this)
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        resources.getString(R.string.failed_msg),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    /**
     * fields validate
     */
    fun validationFields():Boolean{
        if (TextUtils.isEmpty(etUsername?.text)) {
            etUsername?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etUsername?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            setValidate(false)
            return false
        } else {
            etUsername?.setBackgroundResource(R.drawable.shape_field_fill)
            etUsername?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }
        if (TextUtils.isEmpty(etPassword?.text)) {
            etPassword?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etPassword?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            setValidate(false)
            return false
        } else {
            etPassword?.setBackgroundResource(R.drawable.shape_field_fill)
            etPassword?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }
        setValidate(true)
        return true
    }

    /**
     * set enable/disable to button if validation
     */
    fun setValidate(state:Boolean) {
        if (state) {
            //ivLogin?.setBackgroundResource(R.drawable.registration_next)
            ivLogin?.isEnabled=true
        } else {
            //ivLogin?.setBackgroundResource(R.drawable.registration_next_disable)
            ivLogin?.isEnabled=false
        }
    }

    /**
     * make login
     */
    fun doLogin() {
        val email=etUsername?.text.toString()
            //"hag.hispin@gmail.com"//"arielmunk+6@gmail.com"//"App@API_user1!"//binding.txtInputEmail.text.toString()
        val pwd=etPassword?.text.toString()//"1234"//resources.getString(R.string.password)//binding.txtPass.text.toString()
        viewModel.setUser(email, pwd)
        //viewModel.setUser("shayporat1969@gmail.com", "shay1234")
        //viewModel.setUser("hag.hispin11@gmail.com", "123456")
        viewModel.loginUser()
        isAction=true
    }


}