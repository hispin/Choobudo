package com.generosity.choobudo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.generosity.choobudo.R

class RegThirdAssociateFragment : Fragment() {

    var etAssociateAddress :EditText?=null
    var etNumAddress :EditText?=null
    var etCity :EditText?=null
    var etCountry :EditText?=null
    var etPostal:EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        return view
    }


}