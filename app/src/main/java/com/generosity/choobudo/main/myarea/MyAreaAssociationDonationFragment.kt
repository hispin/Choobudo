package com.generosity.choobudo.main.myarea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.generosity.choobudo.R
import com.generosity.choobudo.main.MainScreenViewModel
import com.generosity.choobudo.models.UserAssociationResponse

class MyAreaAssociationDonationFragment : Fragment() {

    private var viewModel: MainScreenViewModel?=null
    var tvUser:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_my_area_association_donation, container, false)
        initView(view)
        viewModel=ViewModelProvider(requireActivity())[MainScreenViewModel::class.java]
        setListener()
        return view
    }

    private fun initView(view: View?) {
        tvUser = view?.findViewById(R.id.tvUser)
    }
    fun setListener() {
        if (activity != null) {
            viewModel?.isEditable?.observe(requireActivity(), Observer {
                if (it) {
                    toggleEditReadOnlyStatus(true)
                } else {
                    toggleEditReadOnlyStatus(false)
                }
            })

            viewModel?.userAssociationResponse?.observe(requireActivity(), Observer {
                var userAssociationResponse=it
                if (userAssociationResponse != null) {
                    fillUserFields(userAssociationResponse)
                }
            })
        }
    }

    private fun fillUserFields(userAssociationResponse: UserAssociationResponse) {
        tvUser?.text=userAssociationResponse.number_of_users.toString()
    }

    private fun toggleEditReadOnlyStatus(isEditStatus: Boolean) {

    }

}