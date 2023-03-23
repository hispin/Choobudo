package com.generosity.choobudo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.generosity.choobudo.R

class RegSecondFragment : Fragment() {

    var etTelNum: EditText?=null
    var etEmail: EditText?=null
    var etCity: EditText?=null
    var etCountry: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_reg_second, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        etTelNum=view?.findViewById(R.id.etTelNum)
        etEmail=view?.findViewById(R.id.etEmail)
        etCity=view?.findViewById(R.id.etCity)
        etCountry=view?.findViewById(R.id.etCountry)
    }


}