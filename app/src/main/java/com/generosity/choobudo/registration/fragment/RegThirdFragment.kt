package com.generosity.choobudo.registration.fragment

import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.generosity.choobudo.R
import com.generosity.choobudo.common.common.Companion.isValidation
import com.generosity.choobudo.common.common.Constant.PASSWORD_VALIDATION
import com.generosity.choobudo.registration.RegisterViewModel
import java.util.regex.Pattern


class RegThirdFragment : Fragment() {

    private var tvTermOfUse: AppCompatTextView?=null
    var etDayBirth: EditText?=null
    var etMonthBirth: EditText?=null
    var etYearBirth: EditText?=null
    var etUser: EditText?=null
    var etPassword: EditText?=null
    var cbIRead: CheckBox?=null
    private var viewModel: RegisterViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(requireActivity())[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_reg_third, container, false)
        initView(view)
        viewModel?.setRegFirstValidate(false)
        return view
    }

    private fun initView(view: View?) {
        tvTermOfUse=view?.findViewById(R.id.tvTermOfUse)
        makeLink()
        etDayBirth=view?.findViewById(R.id.etDayBirth)
        etMonthBirth=view?.findViewById(R.id.etMonthBirth)
        etYearBirth=view?.findViewById(R.id.etYearBirth)
        etUser=view?.findViewById(R.id.etUser)
        etUser?.setText(viewModel?.userContributer?.email)
        etPassword=view?.findViewById(R.id.etPassword)
        cbIRead=view?.findViewById(R.id.cbIRead)

        etDayBirth?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etMonthBirth?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etYearBirth?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etPassword?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        cbIRead?.setOnCheckedChangeListener { buttonView, isChecked ->
            validationFields()
        }
    }

    /**
     * make textview as link
     */
    private fun makeLink() {
        tvTermOfUse?.isClickable=true
        tvTermOfUse?.movementMethod=LinkMovementMethod.getInstance()
        val source=resources.getString(R.string.term_of_use)
        val text="<a href=''>$source </a>"
        tvTermOfUse?.text=Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        tvTermOfUse?.setOnClickListener {
            //TODO
        }
    }

    /**
     * fields validate
     */
    fun validationFields():Boolean{
        if (TextUtils.isEmpty(etDayBirth?.text)) {
            etDayBirth?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etDayBirth?.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        }
        if (TextUtils.isEmpty(etMonthBirth?.text)) {
            etMonthBirth?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etMonthBirth?.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        }
        if (TextUtils.isEmpty(etYearBirth?.text)) {
            etYearBirth?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etYearBirth?.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        }

        if (!TextUtils.isEmpty(etPassword?.text)
            && isValidation(Pattern.compile(PASSWORD_VALIDATION),etPassword?.text.toString())) {
            etPassword?.setBackgroundResource(R.drawable.shape_field_fill)
            etPassword?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        } else {
            etPassword?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etPassword?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        }

        if(cbIRead?.isChecked == false){
            viewModel?.setRegFirstValidate(false)
            return false
        }
        viewModel?.setRegFirstValidate(true)
        return true
    }



}