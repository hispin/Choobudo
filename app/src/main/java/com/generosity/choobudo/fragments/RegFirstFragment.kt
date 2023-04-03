package com.generosity.choobudo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.generosity.choobudo.R
import com.generosity.choobudo.activities.registration.RegisterViewModel


class RegFirstFragment : Fragment() {

    var etUserPName: EditText?=null
    var etUserFName: EditText?=null
    var spAssociation: Spinner?=null
    var etNotFindAssociation: EditText?=null
    private var viewModel: RegisterViewModel?=null
    private var associationName: ArrayList<String>?=null
    private var associationValue: ArrayList<String>?=null
    private var currentPositionTypeAssociation=-1

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
        setListener()
        getAssociations()
        return view
    }

    private fun setListener() {
        viewModel?.associationsResponse?.observe(requireActivity(), Observer {
            for (association in it) {
                associationValue?.add(association.id)
                associationName?.add(association.name)
            }
            fillAssocationSpinner()
        })
    }

    /**
     * fill associations
     */
    private fun fillAssocationSpinner() {
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
                    currentPositionTypeAssociation=position
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun initView(view: View?) {
        etUserPName=view?.findViewById(R.id.etUserPName)
        etUserFName=view?.findViewById(R.id.etAssociationName)
        spAssociation=view?.findViewById(R.id.spAssociation)
        etNotFindAssociation=view?.findViewById(R.id.etNotFindAssociation)
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
        return associationValue?.get(currentPositionTypeAssociation)
    }

}