package com.generosity.choobudo.main.myarea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.generosity.choobudo.common.common
import com.generosity.choobudo.common.common.Constant.USER_ASSOCIATION
import com.generosity.choobudo.common.common.Constant.USER_CONTRIBUTER
import com.generosity.choobudo.common.getIntInPreference
import com.generosity.choobudo.main.MainScreenViewModel


class MyAreaManagerFragment : Fragment() , CallBackMyAreaListener {

    var btnPrivateDetails:Button?=null
    var btnDonationDetails:Button?=null
    var btnEditUserDetails:Button?=null
    var btnSave:Button?=null
    val TAG_CONTRIBUTER="MyAreaContributerFragment"
    val TAG_ASSOCIATION="MyAreaAssociationFragment"
    private var viewModel: MainScreenViewModel?=null
    var userStatus:Int? = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(com.generosity.choobudo.R.layout.fragment_my_area_manager, container, false)
        initView(view)

        viewModel=ViewModelProvider(requireActivity())[MainScreenViewModel::class.java]

        //default
        setPrivateContributingStatus()

        userStatus=checkUser()

        if(userStatus==USER_CONTRIBUTER) {
            openMyFragment(MyAreaContributerFragment.newInstance(false), TAG_CONTRIBUTER)
            setReadOnlyStatus()
        }else if(userStatus== USER_ASSOCIATION) {
            openMyFragment(MyAreaAssociationFragment.newInstance(false), TAG_ASSOCIATION)
            setReadOnlyStatus()
        }

        return view
    }

    /**
     * set edit status
     */
    private fun setEditStatus() {
        btnEditUserDetails?.visibility=View.GONE
        btnSave?.visibility=View.VISIBLE
        viewModel?.setEditable(true)
    }

    /**
     * set read only status
     */
    private fun setReadOnlyStatus() {
        btnEditUserDetails?.visibility=View.VISIBLE
        btnSave?.visibility=View.GONE
        viewModel?.setEditable(false)
    }

    /**
     * get status of user
     */
    private fun checkUser(): Int? {
        return getIntInPreference(requireActivity(), common.Constant.CURRENT_USER_KEY, -1 )
    }

    private fun initView(view: View?) {
        btnPrivateDetails = view?.findViewById(com.generosity.choobudo.R.id.btnPrivateDetails)
        btnPrivateDetails?.setOnClickListener {
            if(userStatus==USER_CONTRIBUTER) {
               setPrivateContributingStatus()
               openMyFragment(MyAreaContributerFragment(),"MyAreaContributerFragment")
            }else if(userStatus== USER_ASSOCIATION) {
                setPrivateContributingStatus()
                openMyFragment(MyAreaAssociationFragment(),"MyAreaAssociationFragment")
            }
        }
        btnDonationDetails = view?.findViewById(com.generosity.choobudo.R.id.btnDonationDetails)
        btnDonationDetails?.setOnClickListener {
            if(userStatus==USER_CONTRIBUTER) {
               setDonationDetailsStatus()
               openMyFragment(MyAreaContributerDonationFragment(),"MyAreaDonationDetailsFragment")
            }else if(userStatus== USER_ASSOCIATION) {
               setDonationDetailsStatus()
                openMyFragment(MyAreaAssociationDonationFragment(),"MyAreaAssociationDonationFragment")

            }
        }
        btnEditUserDetails= view?.findViewById(com.generosity.choobudo.R.id.btnEditUserDetails)
        btnEditUserDetails?.setOnClickListener {
            setEditStatus()
        }
        btnSave= view?.findViewById(com.generosity.choobudo.R.id.btnSave)
        btnSave?.setOnClickListener {
            if(userStatus==USER_CONTRIBUTER) {
                setReadOnlyStatus()
                viewModel?.updateContributer(userStatus)
            }else if(userStatus== USER_ASSOCIATION) {
                setReadOnlyStatus()
                viewModel?.updateAssociation(userStatus)
            }

        }
    }

    fun openMyFragment(myFragment: Fragment,TAG:String){
        val t: FragmentTransaction=childFragmentManager.beginTransaction()
        //val t: FragmentTransaction=childFragmentManager requireActivity().childFragmentManager//.beginTransaction()
        t.replace(com.generosity.choobudo.R.id.frMangerContainer, myFragment,TAG )
        t.addToBackStack(null)
        t.commit()
    }

    /**
     * set contributing status
     */
    private fun setPrivateContributingStatus() {
        btnDonationDetails?.background=ContextCompat.getDrawable(requireActivity(), com.generosity.choobudo.R.drawable.unselected_type_bg)
        btnDonationDetails?.setTextColor(requireActivity().getColor(com.generosity.choobudo.R.color.grey4))
        btnPrivateDetails?.background=ContextCompat.getDrawable(requireActivity(), com.generosity.choobudo.R.drawable.selected_type_bg)
        btnPrivateDetails?.setTextColor(requireActivity().getColor(com.generosity.choobudo.R.color.white))
    }

    /**
     * set association status
     */
    private fun setDonationDetailsStatus() {
        btnPrivateDetails?.background=ContextCompat.getDrawable(requireActivity(), com.generosity.choobudo.R.drawable.unselected_type_bg)
        btnPrivateDetails?.setTextColor(requireActivity().getColor(com.generosity.choobudo.R.color.grey4))
        btnDonationDetails?.background=ContextCompat.getDrawable(requireActivity(), com.generosity.choobudo.R.drawable.selected_type_bg)
        btnDonationDetails?.setTextColor(requireActivity().getColor(com.generosity.choobudo.R.color.white))
    }

    override fun enableUpdate(status: Boolean?) {
        if (status != null) {

            if(status && viewModel?.isEditable?.value == true) {
                btnSave?.visibility=View.VISIBLE
            }else{
                btnSave?.visibility=View.INVISIBLE
            }
        }
    }

}