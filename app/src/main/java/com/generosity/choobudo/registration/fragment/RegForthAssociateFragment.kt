package com.generosity.choobudo.registration.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.generosity.choobudo.R

class RegForthAssociateFragment : Fragment() {

    var etAccountName:EditText?=null
    var etAccountNum:EditText?=null
    var etBranchName:EditText?=null
    var etBranchNum:EditText?=null
    var etBankNum:EditText?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_reg_forth_associate, container, false)

        initView(view)

        return view
    }

    private fun initView(view: View?) {
        etAccountName = view?.findViewById(R.id.etAccountName)
        etAccountNum = view?.findViewById(R.id.etAccountNum)
        etBranchName = view?.findViewById(R.id.etBranchName)
        etBranchNum = view?.findViewById(R.id.etBranchNum)
        etBankNum = view?.findViewById(R.id.etBankNum)
    }
}