package com.generosity.choobudo.registration.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.generosity.choobudo.R
import com.generosity.choobudo.common.common
import com.generosity.choobudo.registration.RegisterViewModel

class RegSecondAssociateFragment : Fragment() {

    var etPNameContact:EditText?=null
    var etFamilyName:EditText?=null
    var etMobile:EditText?=null
    var etEmail:EditText?=null
    private var viewModel: RegisterViewModel?=null
    var emailProgressBar: ProgressBar?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(requireActivity())[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_reg_second_association, container, false)
        initView(view)
        viewModel?.setRegFirstValidate(false)
        return view
    }

    private fun initView(view: View?) {
        etPNameContact = view?.findViewById(R.id.etPNameContact)
        etFamilyName = view?.findViewById(R.id.etFamilyName)
        etMobile = view?.findViewById(R.id.etMobile)
        etEmail = view?.findViewById(R.id.etEmail)
        etPNameContact?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etFamilyName?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etMobile?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etEmail?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                validationFields()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        })
        emailProgressBar=view?.findViewById(R.id.emailProgressBar)
    }

    /**
     * fields validate
     */
    fun validationFields():Boolean{
        if (TextUtils.isEmpty(etPNameContact?.text)) {
            etPNameContact?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etPNameContact?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etPNameContact?.setBackgroundResource(R.drawable.shape_field_fill)
            etPNameContact?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }
        if (TextUtils.isEmpty(etFamilyName?.text)) {
            etFamilyName?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etFamilyName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etFamilyName?.setBackgroundResource(R.drawable.shape_field_fill)
            etFamilyName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }

        if (TextUtils.isEmpty(etMobile?.text)) {
            etMobile?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etMobile?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etMobile?.setBackgroundResource(R.drawable.shape_field_fill)
            etMobile?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }

        if (!TextUtils.isEmpty(etEmail?.text)
            && common.isValidation(Patterns.EMAIL_ADDRESS,etEmail?.text.toString())) {//isUserNameValid(etEmail?.text.toString())) {
            etEmail?.setBackgroundResource(R.drawable.shape_field_fill)
            etEmail?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        } else {
            etEmail?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etEmail?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        }

        viewModel?.setRegFirstValidate(true)
        return true
    }


}