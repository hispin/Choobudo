package com.generosity.choobudo.main.myarea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.generosity.choobudo.R

class MyAreaManagerFragment : Fragment() {

    var btnPrivateDetails:Button?=null
    var btnDonationDetails:Button?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_my_area_manager, container, false)
        initView(view)
        //default
        setPrivateContributingStatus()
        openMyFragment(MyAreaContributerFragment())
        return view
    }

    private fun initView(view: View?) {
        btnPrivateDetails = view?.findViewById(R.id.btnPrivateDetails)
        btnPrivateDetails?.setOnClickListener {
            setPrivateContributingStatus()
            openMyFragment(MyAreaContributerFragment())
        }
        btnDonationDetails = view?.findViewById(R.id.btnDonationDetails)
        btnDonationDetails?.setOnClickListener {
            setDonationDetailsStatus()
            openMyFragment(MyAreaDonationDetailsFragment())
        }
    }

    fun openMyFragment(myFragment: Fragment){
        val t: FragmentTransaction=requireActivity().supportFragmentManager.beginTransaction()
        t.replace(R.id.frMangerContainer, myFragment )
        t.addToBackStack(null)
        t.commit()
    }

    /**
     * set contributing status
     */
    private fun setPrivateContributingStatus() {
        btnDonationDetails?.background=ContextCompat.getDrawable(requireActivity(), R.drawable.unselected_type_bg)
        btnDonationDetails?.setTextColor(requireActivity().getColor(R.color.grey4))
        btnPrivateDetails?.background=ContextCompat.getDrawable(requireActivity(), R.drawable.selected_type_bg)
        btnPrivateDetails?.setTextColor(requireActivity().getColor(R.color.white))
    }

    /**
     * set association status
     */
    private fun setDonationDetailsStatus() {
        btnPrivateDetails?.background=ContextCompat.getDrawable(requireActivity(), R.drawable.unselected_type_bg)
        btnPrivateDetails?.setTextColor(requireActivity().getColor(R.color.grey4))
        btnDonationDetails?.background=ContextCompat.getDrawable(requireActivity(), R.drawable.selected_type_bg)
        btnDonationDetails?.setTextColor(requireActivity().getColor(R.color.white))
    }

}