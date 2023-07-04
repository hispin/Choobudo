package com.generosity.choobudo.main.myarea

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.generosity.choobudo.R
import com.generosity.choobudo.main.MainScreenViewModel
import com.generosity.choobudo.models.Group_donations1
import com.generosity.choobudo.models.UserResponse

class MyAreaDonationDetailsFragment : Fragment() {

    private var currentPosition: Int?=-1
    private var myUserResponse: UserResponse?=null
    private var spOurDonation : Spinner?=null
    private var btnQuarterly:Button?=null
    private var btnAnnual:Button?=null
    private var btnTillThisDay:Button?=null
    private var tvSum:TextView?=null
    private var viewModel: MainScreenViewModel?=null
    private var myDonations: ArrayList<Int>?=null
    private  var callbackListener: MyAreaContributerFragment.CallbackListener?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // check if parent Fragment implements listener
        if (parentFragment is MyAreaContributerFragment.CallbackListener) {
            callbackListener = parentFragment as MyAreaContributerFragment.CallbackListener
        } else {
            throw RuntimeException("The parent fragment must implement OnChildFragmentInteractionListener");
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_my_area_donation_details, container, false)
        initView(view)
        fillDonation()
        viewModel=ViewModelProvider(requireActivity())[MainScreenViewModel::class.java]
        setListener()
        return view
    }

    private fun fillDonation() {
        myDonations=ArrayList(
            listOf(-1,194,195,196)
        )
    }

    private fun initView(view: View?) {
        spOurDonation = view?.findViewById(R.id.spOurDonation)
        spOurDonation?.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long
            ) {
                currentPosition=position
                if (currentPosition != null && currentPosition != -1) {
                    if (validationDonation()) {
                        viewModel?.userResponse?.value?.group_donations=Group_donations1()
                        viewModel?.userResponse?.value?.group_donations?.title=parent.selectedItem.toString()
                        viewModel?.userResponse?.value?.group_donations?.code_number=myDonations?.get(
                                currentPosition!!
                            )!!

                        var n =10
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        btnQuarterly = view?.findViewById(R.id.btnQuarterly)
        btnQuarterly?.setOnClickListener {
            setQuerytatus()
            tvSum?.text=myUserResponse?.donations_sum?.quarterly.toString()
        }
        btnAnnual = view?.findViewById(R.id.btnAnnual)
        btnAnnual?.setOnClickListener {
            setAnnualStatus()
            tvSum?.text=myUserResponse?.donations_sum?.annual.toString()
        }
        btnTillThisDay = view?.findViewById(R.id.btnTillThisDay)
        btnTillThisDay?.setOnClickListener {
            setTillThisDayStatus()
            tvSum?.text=myUserResponse?.donations_sum?.ever.toString()
        }
        tvSum= view?.findViewById(R.id.tvSum)
    }


    /**
     * validation my donation
     */
    fun validationDonation(): Boolean {
        if(myDonations!=null
            && myDonations?.size!! > currentPosition!!){
            if( myDonations?.get(currentPosition!!)?.equals("-1")!!){
                callbackListener?.enableUpdate(false)
                return false
            }else{
                callbackListener?.enableUpdate(true)
                return true
            }
        }
        callbackListener?.enableUpdate(false)
        return false
    }

    /**
     * set query status
     */
    private fun setQuerytatus() {
        btnTillThisDay?.background=ContextCompat.getDrawable(requireActivity(), com.generosity.choobudo.R.drawable.unselected_type_bg)
        btnTillThisDay?.setTextColor(requireActivity().getColor(com.generosity.choobudo.R.color.grey4))
        btnAnnual?.background=ContextCompat.getDrawable(requireActivity(), com.generosity.choobudo.R.drawable.unselected_type_bg)
        btnAnnual?.setTextColor(requireActivity().getColor(com.generosity.choobudo.R.color.grey4))
        btnQuarterly?.background=ContextCompat.getDrawable(requireActivity(), com.generosity.choobudo.R.drawable.selected_type_bg)
        btnQuarterly?.setTextColor(requireActivity().getColor(com.generosity.choobudo.R.color.white))
    }

    /**
     * set this day status
     */
    private fun setTillThisDayStatus() {
        btnAnnual?.background=ContextCompat.getDrawable(requireActivity(), com.generosity.choobudo.R.drawable.unselected_type_bg)
        btnAnnual?.setTextColor(requireActivity().getColor(com.generosity.choobudo.R.color.grey4))
        btnQuarterly?.background=ContextCompat.getDrawable(requireActivity(), com.generosity.choobudo.R.drawable.unselected_type_bg)
        btnQuarterly?.setTextColor(requireActivity().getColor(com.generosity.choobudo.R.color.grey4))
        btnTillThisDay?.background=ContextCompat.getDrawable(requireActivity(), com.generosity.choobudo.R.drawable.selected_type_bg)
        btnTillThisDay?.setTextColor(requireActivity().getColor(com.generosity.choobudo.R.color.white))
    }

    /**
     * set annual status
     */
    private fun setAnnualStatus() {
        btnTillThisDay?.background=ContextCompat.getDrawable(requireActivity(), com.generosity.choobudo.R.drawable.unselected_type_bg)
        btnTillThisDay?.setTextColor(requireActivity().getColor(com.generosity.choobudo.R.color.grey4))
        btnQuarterly?.background=ContextCompat.getDrawable(requireActivity(), com.generosity.choobudo.R.drawable.unselected_type_bg)
        btnQuarterly?.setTextColor(requireActivity().getColor(com.generosity.choobudo.R.color.grey4))
        btnAnnual?.background=ContextCompat.getDrawable(requireActivity(), com.generosity.choobudo.R.drawable.selected_type_bg)
        btnAnnual?.setTextColor(requireActivity().getColor(com.generosity.choobudo.R.color.white))
    }

    private fun setListener() {

        if (activity != null) {

            viewModel?.isEditable?.observe(requireActivity(), Observer {
                if (it) {
                    toggleEditReadOnlyStatus(true)
                } else {
                    toggleEditReadOnlyStatus(false)
                }
            })

            viewModel?.userResponse?.observe(requireActivity(), Observer {
                myUserResponse=it
                fillUserFields(myUserResponse)
            })
        }
    }

    private fun fillUserFields(userResponse: UserResponse?) {
        if(userResponse?.group_donations!=null) {
            var pos=getOurDonation(userResponse.group_donations)
            if (pos != -1) {
                spOurDonation?.setSelection(pos)
            }
            tvSum?.text=userResponse.donations_sum?.quarterly.toString()
        }
    }

    private fun getOurDonation(groupDonations: Group_donations1?): Int {
        return when(groupDonations?.code_number) {
            194-> 1
            195-> 2
            196-> 3
            else -> {0}
        }
    }

    fun toggleEditReadOnlyStatus(isEditStatus: Boolean){
        spOurDonation?.isEnabled = isEditStatus
        btnQuarterly?.isEnabled = isEditStatus
        btnAnnual?.isEnabled = isEditStatus
        btnTillThisDay?.isEnabled = isEditStatus
    }

    /**
     * get duration
     */
    fun getSelectedDuration(): Int {
        when(spOurDonation?.selectedItemPosition){
            1->return 194
            2->return 195
            3->return 196
            else-> return -1
        }
    }

}