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

class RegThirdAssociateFragment : Fragment() {

    var etAssociateAddress :EditText?=null
    var etNumAddress :EditText?=null
    var etCity :EditText?=null
    var etCountry :EditText?=null
    var etPostal:EditText?=null
    private var viewModel: RegisterViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(requireActivity())[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_reg_third_associate, container, false)
        etAssociateAddress = view.findViewById(R.id.etAssociateAddress)
        etNumAddress = view.findViewById(R.id.etNumAddress)
        etCity = view.findViewById(R.id.etCity)
        etCountry = view.findViewById(R.id.etCountry)
        etPostal = view.findViewById(R.id.etPostal)

        etAssociateAddress?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etNumAddress?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etCountry?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etCity?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etPostal?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                validationFields()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        })

        return view
    }

    /**
     * fields validate
     */
    fun validationFields():Boolean{
        if (TextUtils.isEmpty(etAssociateAddress?.text)) {
            etAssociateAddress?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etAssociateAddress?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etAssociateAddress?.setBackgroundResource(R.drawable.shape_field_fill)
            etAssociateAddress?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }
        if (TextUtils.isEmpty(etNumAddress?.text)) {
            etNumAddress?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etNumAddress?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etNumAddress?.setBackgroundResource(R.drawable.shape_field_fill)
            etNumAddress?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
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

        if (TextUtils.isEmpty(etPostal?.text)) {
            etPostal?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etPostal?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etPostal?.setBackgroundResource(R.drawable.shape_field_fill)
            etPostal?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }

        viewModel?.setRegFirstValidate(true)
        return true
    }


}