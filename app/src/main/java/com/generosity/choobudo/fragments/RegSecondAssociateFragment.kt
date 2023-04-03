package com.generosity.choobudo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.generosity.choobudo.R

class RegSecondAssociateFragment : Fragment() {

    var etPNameContact:EditText?=null
    var etFamilyName:EditText?=null
    var etMobile:EditText?=null
    var etEmail:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_reg_second_association, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        etPNameContact = view?.findViewById(R.id.etPNameContact)
        etFamilyName = view?.findViewById(R.id.etFamilyName)
        etMobile = view?.findViewById(R.id.etMobile)
        etEmail = view?.findViewById(R.id.etEmail)
    }


}