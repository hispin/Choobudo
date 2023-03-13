package com.generosity.choobudo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.generosity.choobudo.R

class RegFirstAssociateFragment : Fragment() {

    var btnYes: AppCompatButton?=null
    var btnNo: AppCompatButton?=null

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
    }

}