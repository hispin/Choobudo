package com.generosity.choobudo.main.myarea

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
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
import com.generosity.choobudo.common.common
import com.generosity.choobudo.common.common.Constant.IE_EDITABLE_KEY
import com.generosity.choobudo.main.MainScreenViewModel
import com.generosity.choobudo.models.UserResponse

//private const val ARG_PARAM1="param1"
//private const val ARG_PARAM2="param2"
enum class DATE {
    DAY, MONTH, YEAR
}
/**
 * A simple [Fragment] subclass.
 * Use the [MyAreaContributerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyAreaContributerFragment : Fragment() {
    private var currentPosition: Int?=-1
    private var viewModel: MainScreenViewModel?=null
    private var etUserPName : EditText?=null
    private var etUserFName : EditText?=null
    private var spAssociation : Spinner?=null
    private var etTelNum : EditText?=null
    private var etEmail : EditText?=null
    private var etCity : EditText?=null
    private var etCountry : EditText?=null
    private var etDayBirth : EditText?=null
    private var etMonthBirth : EditText?=null
    private var etYearBirth : EditText?=null
    private var etUser : EditText?=null
    private var associationName: ArrayList<String>?=null
    private var associationValue: ArrayList<String>?=null



    private var isEditable: Boolean?=false

    private  var callbackListener: CallBackMyAreaListener?=null


    override fun onAttach(context: Context) {
        super.onAttach(context)
            // check if parent Fragment implements listener
            if (parentFragment is CallBackMyAreaListener) {
                callbackListener = parentFragment as CallBackMyAreaListener
            } else {
                throw RuntimeException("The parent fragment must implement OnChildFragmentInteractionListener");
            }
    }

    companion object {

        @JvmStatic
        fun newInstance(isEditable: Boolean)=MyAreaContributerFragment().apply {
            arguments=Bundle().apply {
                putBoolean(IE_EDITABLE_KEY, isEditable)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isEditable=it.getBoolean(IE_EDITABLE_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_my_area_contributer, container, false)

        initView(view)

        if(isEditable!=null) {
            toggleEditReadOnlyStatus(isEditable!!)
        }

        viewModel=ViewModelProvider(requireActivity())[MainScreenViewModel::class.java]

        setListener()


        viewModel?.getUser(false)
        viewModel?.getAssociations()

        return view
    }

    private fun setListener() {

        if(activity!=null) {

            viewModel?.userResponse?.observe(requireActivity(), Observer {
                var userResponse=it
                if(userResponse!=null) {
                    fillUserFields(userResponse)
                }
            })

            viewModel?.isEditable?.observe(requireActivity(), Observer {
                if(it){
                    toggleEditReadOnlyStatus(true)
                }else{
                    toggleEditReadOnlyStatus(false)
                }
            })

            viewModel?.associationsResponse?.observe(requireActivity(), Observer {

                if(activity!=null) {
                    associationName=ArrayList()
                    associationValue=ArrayList()

                    associationValue?.add("-1")
                    associationName?.add(requireActivity().resources.getString(R.string.choose_association))
                    for (association in it) {
                        associationValue?.add(association.id)
                        associationName?.add(association.name)
                    }
                    var pos=viewModel?.userResponse?.value?.organization_unique_id?.let { it1 ->
                        getPositionByAssosiationId(
                            it1
                        )
                    }
                    fillAssociationSpinner(pos)
                }
            })
        }
    }

    /**
     * fill fields of user
     */
    private fun fillUserFields(userResponse: UserResponse) {
        etUserPName?.setText(userResponse.first_name)
        etUserFName?.setText(userResponse.last_name)



        etTelNum?.setText(userResponse.phone)
        etEmail?.setText(userResponse.email)
        etCity?.setText(userResponse.city)
        etCountry?.setText(userResponse.state)

        parseBirthDay(userResponse.birthday)
        //etCountry?.setText(userResponse.state)
        //etCountry?.setText(userResponse.state)
        etUser?.setText(userResponse.email)
    }

    /**
     * get position of organization of user
     */
    private fun getPositionByAssosiationId(organizationUniqueId: String): Int? {
        var idx= associationValue?.indexOf(organizationUniqueId)
        if (idx != null && associationName?.size!=null) {
            if(idx >= 0 && idx < associationName?.size!!){
                  return idx
            }
        }
        return null
    }

    private fun parseBirthDay(birthday: String) {
         var arr=birthday.split("/")
         if(arr.size>2){
             etDayBirth?.setText(arr[0])
             etMonthBirth?.setText(arr[1])
             etYearBirth?.setText(arr[2])
         }
    }

    private fun initView(view: View?) {
        etUserPName = view?.findViewById(R.id.etUserPName)
        etUserFName = view?.findViewById(R.id.etUserFName)
        spAssociation = view?.findViewById(R.id.spAssociation)
        etTelNum = view?.findViewById(R.id.etTelNum)
        etEmail = view?.findViewById(R.id.etEmail)
        etCity = view?.findViewById(R.id.etCity)
        etCountry = view?.findViewById(R.id.etCountry)
        etDayBirth = view?.findViewById(R.id.etDayBirth)
        etMonthBirth = view?.findViewById(R.id.etMonthBirth)
        etYearBirth = view?.findViewById(R.id.etYearBirth)
        etUser = view?.findViewById(R.id.etUser)
    }

    /**
     * toggle fields as enable/disabled
     */
    fun toggleEditReadOnlyStatus(isEditStatus: Boolean){
        etUserPName?.isEnabled = isEditStatus
        etUserFName?.isEnabled = isEditStatus
        spAssociation?.isEnabled = isEditStatus
        etTelNum?.isEnabled = isEditStatus
        etEmail?.isEnabled = isEditStatus
        etCity?.isEnabled = isEditStatus
        etCountry?.isEnabled = isEditStatus
        etDayBirth?.isEnabled = isEditStatus
        etMonthBirth?.isEnabled = isEditStatus
        etYearBirth?.isEnabled = isEditStatus
        etUser?.isEnabled = isEditStatus
        if(isEditStatus){
            etUserPName?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if(validationFields(etUserPName)) {
                        viewModel?.userResponse?.value?.first_name=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            })
            etUserFName?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if(validationFields(etUserFName)) {
                        viewModel?.userResponse?.value?.last_name=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            })
            //associations
            spAssociation?.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>, view: View, position: Int, id: Long
                ) {
                    currentPosition=position
                    if(validationAssociation()){
                        viewModel?.userResponse?.value?.organization_unique_id=associationValue?.get(
                            currentPosition!!
                        ).toString()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
            etTelNum?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if(validationFields(etTelNum)) {
                        viewModel?.userResponse?.value?.phone=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            })
            etEmail?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if(validateEmail(etEmail)) {
                        viewModel?.userResponse?.value?.email=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            })
            etCity?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if(validationFields(etCity)) {
                        viewModel?.userResponse?.value?.city=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            })
            etCountry?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if(validationFields(etCountry)) {
                        viewModel?.userResponse?.value?.state=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            })
            etUser?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if(validateEmail(etUser)) {
                        viewModel?.userResponse?.value?.email=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            })
        }
        etDayBirth?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if(validationFields(etDayBirth)) {
                    updateDate(DATE.DAY,s.toString())
                }
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        })
        etMonthBirth?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if(validationFields(etMonthBirth)) {
                    updateDate(DATE.MONTH,s.toString())
                }
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        })
        etYearBirth?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if(validationFields(etYearBirth)) {
                    updateDate(DATE.YEAR,s.toString())
                }
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        })
    }

    /**
     * update date
     */
    fun updateDate(status: DATE, s:String){
        val arr=viewModel?.userResponse?.value?.birthday?.split("/")
            ?.let { ArrayList(it) }
        if(arr!=null && arr.size > 2 ){
            when(status){
                DATE.DAY->arr[0] = s
                DATE.MONTH->arr[1] = s
                DATE.YEAR->arr[2] = s
                else -> {}
            }

        }
        viewModel?.userResponse?.value?.birthday=arr!![0]+"/"+arr[1]+"/"+arr[2]
    }

    /**
     * fill associations
     */
    private fun fillAssociationSpinner(pos: Int?) {
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
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    //currentPosition=position
                    //validationFields()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // write code to perform some action
                }
            }
            if (pos != null) {
                spAssociation?.setSelection(pos)
            }
        }
    }

    /**
     * validate email
     */
    fun validateEmail(etAny: EditText?): Boolean {
        if (!TextUtils.isEmpty(etAny?.text)
            && common.isValidation(Patterns.EMAIL_ADDRESS,etAny?.text.toString())) {//isUserNameValid(etEmail?.text.toString())) {
            etAny?.setBackgroundResource(R.drawable.shape_field_fill)
            etAny?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
            callbackListener?.enableUpdate(true)
            return true
        } else {
            etAny?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etAny?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            callbackListener?.enableUpdate(false)
            return false
        }
    }

    /**
     * validation association
     */
    fun validationAssociation(): Boolean {
        if(associationValue!=null
            && associationValue?.size!! > currentPosition!!){
                if( associationValue?.get(currentPosition!!)?.equals("-1")!!){
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
     * fields validate
     */
    fun validationFields(etAny: EditText?):Boolean{
        if (TextUtils.isEmpty(etAny?.text)) {
            etAny?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etAny?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            callbackListener?.enableUpdate(false)
            //viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etAny?.setBackgroundResource(R.drawable.shape_field_fill)
            etAny?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
            callbackListener?.enableUpdate(true)
        }
        return true
    }
}