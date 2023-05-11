package com.generosity.choobudo.registration.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.generosity.choobudo.R
import com.generosity.choobudo.registration.RegisterViewModel

class RegFirstAssociateFragment : Fragment() {


    var haveSection46:Boolean?=null
    var btnYes: AppCompatButton?=null
    var btnNo: AppCompatButton?=null
    var spTypeOrganization:Spinner?=null
    var etAssociationName:EditText?=null
    var etContactPosition:EditText?=null
    private var viewModel: RegisterViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(requireActivity())[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_reg_first_associate, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        btnYes=view?.findViewById(R.id.btnYes)
        btnYes?.setOnClickListener {
            setConfirm()
        }
        btnNo=view?.findViewById(R.id.btnNo)
        btnNo?.setOnClickListener {
            setUnConfirm()
        }
        spTypeOrganization = view?.findViewById(R.id.spTypeOrganization)
        etAssociationName = view?.findViewById(R.id.etAssociationName)
        etAssociationName?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }

        etContactPosition = view?.findViewById(R.id.etContactPosition)
        etContactPosition?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                validationFields()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        })

        spTypeOrganization?.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long
            ) {
                validationFields()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    /**
     * set un confirm
     */
    private fun setUnConfirm() {
        btnYes?.background=
            ContextCompat.getDrawable(requireActivity(), R.drawable.unselected_type_bg)
        btnYes?.setTextColor(requireActivity().getColor(R.color.grey4))
        btnNo?.background=
            ContextCompat.getDrawable(requireActivity(), R.drawable.selected_type_bg_stage46)
        btnNo?.setTextColor(requireActivity().getColor(R.color.white))
        haveSection46=false
    }

    /**
     * set confirm
     */
    private fun setConfirm() {
        btnYes?.background=
            ContextCompat.getDrawable(requireActivity(), R.drawable.selected_type_bg_stage46)
        btnYes?.setTextColor(requireActivity().getColor(R.color.white))
        btnNo?.background=
            ContextCompat.getDrawable(requireActivity(), R.drawable.unselected_type_bg)
        btnNo?.setTextColor(requireActivity().getColor(R.color.grey4))
        haveSection46=true
    }

    /**
     * get selected type association
     */
    fun getSelectedTypeAssociation(): Int {
        when(spTypeOrganization?.selectedItemPosition){
            1->return 183
            2->return 184
            3->return 185
            4->return 186
            else-> return -1
        }
    }

    /**
     * fields validate
     */
    fun validationFields():Boolean{
        if (TextUtils.isEmpty(etAssociationName?.text)) {
            etAssociationName?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etAssociationName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etAssociationName?.setBackgroundResource(R.drawable.shape_field_fill)
            etAssociationName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }

        if (TextUtils.isEmpty(etContactPosition?.text)) {
            etContactPosition?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etContactPosition?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etContactPosition?.setBackgroundResource(R.drawable.shape_field_fill)
            etContactPosition?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }

        if(getSelectedTypeAssociation()==-1){
            viewModel?.setRegFirstValidate(false)
            return false
        }
//        if (TextUtils.isEmpty(etUserFName?.text)) {
//            etUserFName?.setBackgroundResource(R.drawable.shape_field_invalidate)
//            etUserFName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
//            viewModel?.setRegFirstValidate(false)
//            return false
//        } else {
//            etUserFName?.setBackgroundResource(R.drawable.shape_field_fill)
//            etUserFName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
//        }
//        if(associationValue!=null
//            && associationValue?.size!! >currentPosition
//            && associationValue?.get(currentPosition)?.equals("-1")!!){
//
//            if (TextUtils.isEmpty(etNotFindAssociation?.text)) {
//                viewModel?.setRegFirstValidate(false)
//                return false
//            }
//        }
        viewModel?.setRegFirstValidate(true)
        return true
    }

}