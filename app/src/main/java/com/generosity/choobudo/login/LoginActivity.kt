package com.generosity.choobudo.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.generosity.choobudo.R
import com.generosity.choobudo.activities.BaseActivity
import com.generosity.choobudo.common.common.Companion.isValidation
import com.generosity.choobudo.common.common.Companion.openMainScreen
import com.generosity.choobudo.common.common.Constant.FORGOT_PASSWORD_LOGIN
import com.generosity.choobudo.common.common.Constant.NORMAL_LOGIN
import com.generosity.choobudo.common.common.Constant.PASS_KEY
import com.generosity.choobudo.common.common.Constant.RESULT_RESET_PASSWORD_SUCCESS
import com.generosity.choobudo.common.common.Constant.USER_KEY
import com.generosity.choobudo.common.setStringInPreference
import com.generosity.choobudo.registration.RegisterActivity

class LoginActivity : BaseActivity() {

    //private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<LoginViewModel>()
    private var isAction=false
    private var etUsername:EditText?=null
    private var etPassword:EditText?=null
    private var ivLogin: ImageView?=null
    private var ivRegistration:ImageView?=null
    private var tvForgetPassword:TextView?=null
    private var tvUser:TextView?=null
    private var tvPassword:TextView?=null
    private var tvConnection:TextView?=null
    private var pbLogin:ProgressBar?=null
    private var loginStatus = NORMAL_LOGIN
    private var tvError:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvError= findViewById(R.id.tvError)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        pbLogin = findViewById(R.id.pbLogin)
        ivLogin = findViewById(R.id.ivLogin)
        ivLogin?.isEnabled=false
        ivLogin?.setOnClickListener {
            doLogin()
        }

        ivRegistration= findViewById(R.id.ivRegistration)
        ivRegistration?.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
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

        tvUser = findViewById(R.id.tvUser)
        tvPassword = findViewById(R.id.tvPassword)
        tvConnection = findViewById(R.id.tvConnection)

        tvForgetPassword= findViewById(R.id.tvForgetPassword)
        tvForgetPassword?.setOnClickListener {
            if(loginStatus== NORMAL_LOGIN){
                setForgetPasswordStatus()
            }else if(loginStatus== FORGOT_PASSWORD_LOGIN){
                setNormalLoginStatus()
            }
        }

        setValidate(false)

       setListener()
    }

    /**
     * set normal login status
     */
    private fun setNormalLoginStatus() {
        tvError?.visibility = View.GONE
        tvUser?.visibility = View.VISIBLE
        etUsername?.visibility = View.VISIBLE
        tvPassword?.text=resources.getString(R.string.password)
        tvForgetPassword?.text=resources.getString(R.string.forgot_user)
        tvConnection?.text=resources.getString(R.string.connect)
        ivRegistration?.visibility = View.VISIBLE
        loginStatus=NORMAL_LOGIN
        etPassword?.inputType=InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        etPassword?.setText("")
    }

    /**
     * set forgot password login status
     */
    private fun setForgetPasswordStatus() {
        tvError?.visibility = View.VISIBLE
        tvUser?.visibility = View.INVISIBLE
        etUsername?.visibility = View.INVISIBLE
        tvPassword?.text=resources.getString(R.string.email)
        tvForgetPassword?.text=resources.getString(R.string.i_remembered)
        tvConnection?.text=resources.getString(R.string.reset_password)
        ivRegistration?.visibility = View.INVISIBLE
        loginStatus=FORGOT_PASSWORD_LOGIN
        etPassword?.inputType=InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }

    /**
     * handle view model observe
     */
    private fun setListener() {

        viewModel.resultRequest?.observe(this, Observer {
            if(isAction && it==RESULT_RESET_PASSWORD_SUCCESS){
                isAction=false
                pbLogin?.visibility=View.INVISIBLE
                tvError?.text = resources.getString(R.string.response_reset_password)
            }else{
                tvError?.text = resources.getString(R.string.error)
            }
        })

        viewModel.isSuccess?.observe(this, Observer {

            if (isAction) {
                isAction=false
                if (it) {
                    Toast.makeText(
                        this@LoginActivity,
                        resources.getString(R.string.success_msg),
                        Toast.LENGTH_SHORT
                    ).show()
                    setStringInPreference(this,USER_KEY,etUsername?.text.toString())
                    setStringInPreference(this,PASS_KEY,etPassword?.text.toString())
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

        viewModel.errorMsg?.observe(this, Observer {
            if (isAction) {
                if (it.isNotEmpty()) {
                    Toast.makeText(
                        this@LoginActivity,
                        it,
                        Toast.LENGTH_SHORT
                    ).show()
                    //openMainScreen(this)
                }
            }
        })
    }

    /**
     * fields validate
     */
    fun validationFields():Boolean{
        if(loginStatus== NORMAL_LOGIN) {
            if (!TextUtils.isEmpty(etUsername?.text) && isValidation(
                    Patterns.EMAIL_ADDRESS,
                    etUsername?.text.toString()
                )
            ) {
                etUsername?.setBackgroundResource(R.drawable.shape_field_fill)
                etUsername?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
            } else {
                etUsername?.setBackgroundResource(R.drawable.shape_field_invalidate)
                etUsername?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
                setValidate(false)
                return false
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
        }else if(loginStatus== FORGOT_PASSWORD_LOGIN) {
            if (!TextUtils.isEmpty(etPassword?.text) && isValidation(
                    Patterns.EMAIL_ADDRESS,
                    etPassword?.text.toString()
                )
            ) {
                etPassword?.setBackgroundResource(R.drawable.shape_field_fill)
                etPassword?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
            } else {
                etPassword?.setBackgroundResource(R.drawable.shape_field_invalidate)
                etPassword?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
                setValidate(false)
                return false
            }
        }
        setValidate(true)
        return true
    }

    /**
     * set enable/disable to button if validation
     */
    fun setValidate(state:Boolean) {
        ivLogin?.isEnabled=state
    }

    /**
     * make login
     */
    fun doLogin() {
        if(loginStatus == NORMAL_LOGIN) {
            val email=etUsername?.text.toString()
            //"hag.hispin@gmail.com"//"arielmunk+6@gmail.com"//"App@API_user1!"//binding.txtInputEmail.text.toString()
            val pwd=etPassword?.text.toString()//"1234"//resources.getString(R.string.password)//binding.txtPass.text.toString()
            viewModel.setUser(email, pwd)
            viewModel.loginUser()
            isAction=true
        }else if(loginStatus == FORGOT_PASSWORD_LOGIN) {
            viewModel.resetPassword(etPassword?.text.toString())
            isAction=true
            pbLogin?.visibility=View.VISIBLE
        }
    }


}