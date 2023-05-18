package com.generosity.choobudo.registration.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.generosity.choobudo.R
import com.generosity.choobudo.registration.RegisterViewModel

class RegForthAssociateFragment : Fragment() {

    var etAccountName:EditText?=null
    var etAccountNum:EditText?=null
    var etBranchName:EditText?=null
    var etBranchNum:EditText?=null
    var etBankNum:EditText?=null
    private var viewModel: RegisterViewModel?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(requireActivity())[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_reg_forth_associate, container, false)

        initView(view)

        viewModel?.setRegFirstValidate(false)

        return view
    }

    private fun initView(view: View?) {
        etAccountName = view?.findViewById(R.id.etAccountName)
        etAccountNum = view?.findViewById(R.id.etAccountNum)
        etBranchName = view?.findViewById(R.id.etBranchName)
        etBranchNum = view?.findViewById(R.id.etBranchNum)
        etBankNum = view?.findViewById(R.id.etBankNum)
        etAccountName?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etAccountNum?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etBranchName?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etBranchNum?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etBankNum?.addTextChangedListener(object : TextWatcher {
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
        if (TextUtils.isEmpty(etAccountName?.text)) {
            etAccountName?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etAccountName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etAccountName?.setBackgroundResource(R.drawable.shape_field_fill)
            etAccountName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }
        if (TextUtils.isEmpty(etAccountNum?.text)) {
            etAccountNum?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etAccountNum?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etAccountNum?.setBackgroundResource(R.drawable.shape_field_fill)
            etAccountNum?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }

        if (TextUtils.isEmpty(etBranchName?.text)) {
            etBranchName?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etBranchName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etBranchName?.setBackgroundResource(R.drawable.shape_field_fill)
            etBranchName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }

        if (TextUtils.isEmpty(etBranchNum?.text)) {
            etBranchNum?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etBranchNum?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etBranchNum?.setBackgroundResource(R.drawable.shape_field_fill)
            etBranchNum?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }

        if (TextUtils.isEmpty(etBankNum?.text)) {
            etBankNum?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etBankNum?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etBankNum?.setBackgroundResource(R.drawable.shape_field_fill)
            etBankNum?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }

        viewModel?.setRegFirstValidate(true)
        return true
    }
}