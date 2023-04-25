package com.generosity.choobudo.registration.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.generosity.choobudo.R
import com.generosity.choobudo.registration.RegisterViewModel


class RegFirstFragment : Fragment() {

    var etUserPName: EditText?=null
    var etUserFName: EditText?=null
    var spAssociation: Spinner?=null
    var etNotFindAssociation: EditText?=null
    private var viewModel: RegisterViewModel?=null
    private var associationName: ArrayList<String>?=null
    private var associationValue: ArrayList<String>?=null
    private var currentPositionTypeAssociation=-1
    private var currentPosition =-1

    /**
     * fields validate
     */
    fun validationFields():Boolean{
            if (TextUtils.isEmpty(etUserPName?.text)) {
                etUserPName?.setBackgroundResource(R.drawable.shape_field_invalidate)
                etUserPName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
                viewModel?.setRegFirstValidate(false)
                return false
            } else {
                etUserPName?.setBackgroundResource(R.drawable.shape_field_fill)
                etUserPName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
            }
            if (TextUtils.isEmpty(etUserFName?.text)) {
                etUserFName?.setBackgroundResource(R.drawable.shape_field_invalidate)
                etUserFName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
                viewModel?.setRegFirstValidate(false)
                return false
            } else {
                etUserFName?.setBackgroundResource(R.drawable.shape_field_fill)
                etUserFName?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
            }
            if(associationValue!=null
                && associationValue?.size!! >currentPosition
                && associationValue?.get(currentPosition)?.equals("-1")!!){

                if (TextUtils.isEmpty(etNotFindAssociation?.text)) {
                    viewModel?.setRegFirstValidate(false)
                    return false
                }
            }
        viewModel?.setRegFirstValidate(true)
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        getAssociations()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(requireActivity())[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_reg_first, container, false)
        associationName=ArrayList<String>()
        associationValue=ArrayList<String>()
        initView(view)
        return view
    }

    private fun setListener() {
        if(activity!=null) {
            viewModel?.associationsResponse?.observe(requireActivity(), Observer {
                associationValue?.add("-1")
                associationName?.add(requireActivity().resources.getString(R.string.choose_association))
                for (association in it) {
                    associationValue?.add(association.id)
                    associationName?.add(association.name)
                }
                fillAssociationSpinner()
            })
        }
    }

    /**
     * fill associations
     */
    private fun fillAssociationSpinner() {
        if (spAssociation != null && activity!=null) {
            val adapter=ArrayAdapter(
                requireActivity(),
                android.R.layout.simple_spinner_item,
                associationName?.toArray() as Array<out Any>
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spAssociation?.adapter=adapter

            spAssociation?.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>, view: View, position: Int, id: Long
                ) {
                    currentPosition=position
                    validationFields()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun initView(view: View?) {
        etUserPName=view?.findViewById(R.id.etUserPName)
        etUserPName?.onFocusChangeListener=OnFocusChangeListener { v, hasFocus ->
                validationFields()
        }

        etUserFName=view?.findViewById(R.id.etAssociationName)
        etUserFName?.onFocusChangeListener=OnFocusChangeListener { v, hasFocus ->
            if(hasFocus) {
                validationFields()
            }
        }
        spAssociation=view?.findViewById(R.id.spAssociation)

        etNotFindAssociation=view?.findViewById(R.id.etNotFindAssociation)

        etNotFindAssociation?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                validationFields()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        })
    }

    /**
     * get associations
     */
    private fun getAssociations() {
        viewModel?.getAssociations()
    }

    /**
     * get the current id of selected association
     */
    fun getSelectedAssociation(): String? {
        if( currentPosition>=0 && currentPosition < associationValue?.size!!)
           return associationName?.get(currentPosition)
        else
            return etNotFindAssociation?.text.toString()
    }

}