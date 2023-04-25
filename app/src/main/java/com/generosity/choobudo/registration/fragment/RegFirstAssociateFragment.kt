package com.generosity.choobudo.registration.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.generosity.choobudo.R

class RegFirstAssociateFragment : Fragment() {


    var haveSection46:Boolean?=null
    var btnYes: AppCompatButton?=null
    var btnNo: AppCompatButton?=null
    var spTypeOrganization:Spinner?=null
    var etAssociationName:EditText?=null
    var etContactPosition:EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        etContactPosition = view?.findViewById(R.id.etContactPosition)
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
            0->return 183
            1->return 184
            2->return 185
            3->return 186
            else-> return -1
        }
    }

}