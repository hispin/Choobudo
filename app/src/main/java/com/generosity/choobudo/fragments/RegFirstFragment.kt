package com.generosity.choobudo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.generosity.choobudo.R


class RegFirstFragment : Fragment() {

    var etUserPName: EditText?=null
    var etUserFName: EditText?=null
    var spAssociation: Spinner?=null
    var etNotFindAssociation: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_reg_first, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        etUserPName=view?.findViewById(R.id.etUserPName)
        etUserFName=view?.findViewById(R.id.etUserFName)
        spAssociation=view?.findViewById(R.id.spAssociation)
        etNotFindAssociation=view?.findViewById(R.id.etNotFindAssociation)
    }

}