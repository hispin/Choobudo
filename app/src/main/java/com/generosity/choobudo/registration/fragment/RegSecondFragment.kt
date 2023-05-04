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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.generosity.choobudo.R
import com.generosity.choobudo.common.common
import com.generosity.choobudo.registration.RegisterViewModel

class RegSecondFragment : Fragment() {

    var etTelNum: EditText?=null
    var etEmail: EditText?=null
    var etCity: EditText?=null
    var etCountry: EditText?=null
    private var viewModel: RegisterViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(requireActivity())[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_reg_second, container, false)
        initView(view)
        viewModel?.setRegFirstValidate(false)
        return view
    }

    private fun initView(view: View?) {
        etTelNum=view?.findViewById(R.id.etTelNum)
        etEmail=view?.findViewById(R.id.etEmail)
        etCity=view?.findViewById(R.id.etCity)
        etCountry=view?.findViewById(R.id.etCountry)
        etTelNum?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etEmail?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etCity?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etCountry?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                validationFields()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        })
    }

    /**
     * fields validate
     */
    fun validationFields():Boolean{
        if (TextUtils.isEmpty(etTelNum?.text)) {
            etTelNum?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etTelNum?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etTelNum?.setBackgroundResource(R.drawable.shape_field_fill)
            etTelNum?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
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
        if (TextUtils.isEmpty(etCity?.text)) {
            etCity?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etCity?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etCity?.setBackgroundResource(R.drawable.shape_field_fill)
            etCity?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }
        if (TextUtils.isEmpty(etCountry?.text)) {
            etCountry?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etCountry?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etCountry?.setBackgroundResource(R.drawable.shape_field_fill)
            etCountry?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }
        viewModel?.setRegFirstValidate(true)
        return true
    }


}