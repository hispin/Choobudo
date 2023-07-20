package com.generosity.choobudo.main.myarea

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.generosity.choobudo.R
import com.generosity.choobudo.common.common
import com.generosity.choobudo.common.common.Constant.INTRUDUCTION_IMAGE
import com.generosity.choobudo.main.MainScreenViewModel
import com.generosity.choobudo.models.UserAssociationResponse
import java.io.ByteArrayOutputStream


class MyAreaAssociationFragment : Fragment() {

    private var currentPosition: Int?=-1
    private var viewModel: MainScreenViewModel?=null
    private var spTypeOrganization : Spinner?=null
    private var etAssociation : EditText?=null
    private var btnNo:Button?=null
    private var btnYes:Button?=null
    private var etContactPosition : EditText?=null
    private var etPNameContact : EditText?=null
    private var etFamilyName : EditText?=null
    private var etMobile : EditText?=null
    private var etEmail : EditText?=null
    private var etAssociateAddress : EditText?=null
    private var etNumAddress : EditText?=null
    private var etCity : EditText?=null
    private var etCountry: EditText?=null
    private var etPostal: EditText?=null
    private var etAccountName: EditText?=null
    private var etAccountNum: EditText?=null
    private var etBranchName: EditText?=null
    private var etBranchNum: EditText?=null
    private var ivAddGallery: ImageView?=null
    private var etUserName: EditText?=null
    //private var etNumUsers: EditText?=null
    private var etPersonalAssociationLink: EditText?=null
    private var etBankNum: EditText?=null

    private var associationName: ArrayList<String>?=null
    private var associationValue: ArrayList<String>?=null


    private var isEditable: Boolean?=false

    private  var callbackListener: CallBackMyAreaListener?=null

    var photo64: String?=null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        // check if parent Fragment implements listener
        if (parentFragment is CallBackMyAreaListener) {
            callbackListener = parentFragment as CallBackMyAreaListener
        } else {
            throw RuntimeException("The parent fragment must implement OnChildFragmentInteractionListener");
        }
    }


    private val launcher: ActivityResultLauncher<Intent> = registerForActivityResult<Intent, androidx.activity.result.ActivityResult>(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback<ActivityResult> { result: ActivityResult ->
            if ((result.resultCode == Activity.RESULT_OK && result.data != null)) {
                if (result.data != null) {
                    val photoUri: Uri?=result.data!!.data
                    if(photoUri!=null) {
                        val bitmap=if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            ImageDecoder.decodeBitmap(
                                ImageDecoder.createSource(
                                    requireContext().contentResolver,
                                    photoUri
                                )
                            )
                        } else {
                            MediaStore.Images.Media.getBitmap(
                                requireContext().contentResolver,
                                photoUri
                            )
                        }
                        ivAddGallery?.setImageBitmap(bitmap)
                        if (bitmap != null) {
                            photo64=convertBitmapToBase64(bitmap)
                            viewModel?.userAssociationResponse?.value?.image  = INTRUDUCTION_IMAGE+photo64
                        }
                    }
                }
            }
        })

    /**
     * convert bitmap to base 64
     */
    fun convertBitmapToBase64(bitmap: Bitmap): String? {
        val outputStream=ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    }

    /**
     * convert Uri to Base64
     */
    private fun encodeImage(imageUri: Uri): String? {
        val imageStream = requireActivity().contentResolver.openInputStream(imageUri)
        val selectedImage = BitmapFactory.decodeStream(imageStream)
        val baos=ByteArrayOutputStream()
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b: ByteArray=baos.toByteArray()
        var test= Base64.encodeToString(b, Base64.DEFAULT)
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    companion object {

        @JvmStatic
        fun newInstance(isEditable: Boolean)=MyAreaAssociationFragment().apply {
            arguments=Bundle().apply {
                putBoolean(common.Constant.IE_EDITABLE_KEY, isEditable)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isEditable=it.getBoolean(common.Constant.IE_EDITABLE_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_my_area_association, container, false)
        initView(view)
        if(isEditable!=null) {
            toggleEditReadOnlyStatus(isEditable!!)
        }

        viewModel=ViewModelProvider(requireActivity())[MainScreenViewModel::class.java]

        setListener()

        viewModel?.getAssociationUser()

        return view
    }

    private fun initView(view: View?) {
        spTypeOrganization = view?.findViewById(R.id.spTypeOrganization)
        etAssociation = view?.findViewById(R.id.etAssociation)
        btnNo = view?.findViewById(R.id.btnNo)
        btnYes = view?.findViewById(R.id.btnYes)
        etBankNum = view?.findViewById(R.id.etBankNum)
        etPNameContact = view?.findViewById(R.id.etPNameContact)
        etContactPosition = view?.findViewById(R.id.etContactPosition)
        etFamilyName = view?.findViewById(R.id.etFamilyName)
        etMobile = view?.findViewById(R.id.etMobile)
        etEmail = view?.findViewById(R.id.etEmail)
        etNumAddress = view?.findViewById(R.id.etNumAddress)
        etCity = view?.findViewById(R.id.etCity)
        etCountry = view?.findViewById(R.id.etCountry)
        etAssociateAddress = view?.findViewById(R.id.etAssociateAddress)
        etPostal = view?.findViewById(R.id.etPostal)
        etAccountName = view?.findViewById(R.id.etAccountName)
        etAccountNum = view?.findViewById(R.id.etAccountNum)
        etBranchName = view?.findViewById(R.id.etBranchName)
        etBranchNum = view?.findViewById(R.id.etBranchNum)
        ivAddGallery = view?.findViewById(R.id.ivAddGallery)
        ivAddGallery?.setOnClickListener {
            val intent=Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            launcher.launch(intent)
        }
        etUserName = view?.findViewById(R.id.etUserName)
        //etNumUsers = view?.findViewById(R.id.etNumUsers)
        etPersonalAssociationLink = view?.findViewById(R.id.etPersonalAssociationLink)
    }

    /**
     * get position by selected type association
     */
    fun getPositionBySelectedTypeAssociation(): Int {
        val type= viewModel?.userAssociationResponse?.value?.organization_type?.code_number
        if(type!=null) {
            when (type) {
                183 -> return 1
                184 -> return 2
                185 -> return 3
                186 -> return 4
                else -> return -1
            }
        }
        return -1
    }
    /**
     * get selected type association
     */
    fun getSelectedTypeAssociation(): Int {
        when(spTypeOrganization?.selectedItemPosition){
            1->return 183
            2->return 184
            3->return 185
            4->return 186
            else-> return -1
        }
    }


    /**
     * toggle fields as enable/disabled
     */
    fun toggleEditReadOnlyStatus(isEditStatus: Boolean) {
        spTypeOrganization?.isEnabled=isEditStatus
        etAssociation?.isEnabled=isEditStatus
        btnNo?.isEnabled=isEditStatus
        btnYes?.isEnabled=isEditStatus

        btnYes?.setOnClickListener {
            setConfirm()
        }
        btnNo?.setOnClickListener {
            setUnConfirm()
        }

        etEmail?.isEnabled=false
        etCity?.isEnabled=isEditStatus
        etCountry?.isEnabled=isEditStatus
        etBankNum?.isEnabled=isEditStatus
        etPNameContact?.isEnabled=isEditStatus
        etContactPosition?.isEnabled=isEditStatus
        etFamilyName?.isEnabled=isEditStatus
        etMobile?.isEnabled=isEditStatus
        etAssociateAddress?.isEnabled=isEditStatus
        etPostal?.isEnabled=isEditStatus
        etAccountName?.isEnabled=isEditStatus
        etAccountNum?.isEnabled=isEditStatus
        etBranchName?.isEnabled=isEditStatus
        etBranchNum?.isEnabled=isEditStatus
        ivAddGallery?.isEnabled=isEditStatus
        etUserName?.isEnabled=false
        //etNumUsers?.isEnabled=isEditStatus
        etPersonalAssociationLink?.isEnabled=isEditStatus

        if(isEditStatus) {
            etAssociation?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etAssociation)) {
                        viewModel?.userAssociationResponse?.value?.organization_name=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })
            //association type
            spTypeOrganization?.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>, view: View, position: Int, id: Long
                ) {
                    currentPosition=position
                    if (getSelectedTypeAssociation()!=-1) {
                        viewModel?.userAssociationResponse?.value?.organization_type?.code_number=getSelectedTypeAssociation()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

            etContactPosition?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etContactPosition)) {
                        viewModel?.userAssociationResponse?.value?.contact_position=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })

            etPNameContact?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etPNameContact)) {
                        viewModel?.userAssociationResponse?.value?.contact_first_name=s.toString()
                        var n =10
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })

            etFamilyName?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etFamilyName)) {
                        viewModel?.userAssociationResponse?.value?.contact_last_name=s.toString()
                        var n =10
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })

            etMobile?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etMobile)) {
                        viewModel?.userAssociationResponse?.value?.phone=s.toString()
                        var n =10
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })

            etEmail?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etEmail)) {
                        viewModel?.userAssociationResponse?.value?.email=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })

            etAssociateAddress?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etAssociateAddress)) {
                        viewModel?.userAssociationResponse?.value?.street=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })
            etNumAddress?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etNumAddress)) {
                        viewModel?.userAssociationResponse?.value?.home_num=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })
            etCity?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etCity)) {
                        viewModel?.userAssociationResponse?.value?.city=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })
            etCountry?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etCountry)) {
                        viewModel?.userAssociationResponse?.value?.state=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })
            etPostal?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etPostal)) {
                        viewModel?.userAssociationResponse?.value?.zip_code=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })
            etAccountName?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etAccountName)) {
                        viewModel?.userAssociationResponse?.value?.bank_account_name=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })
            etAccountNum?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etAccountNum)) {
                        viewModel?.userAssociationResponse?.value?.bank_account_num=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })
            etBranchName?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etBranchName)) {
                        viewModel?.userAssociationResponse?.value?.bank_branch_name=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })
            etBranchNum?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etBranchNum)) {
                        viewModel?.userAssociationResponse?.value?.bank_branch_num=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })
            etPersonalAssociationLink?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    if (validationFields(etPersonalAssociationLink)) {
                        viewModel?.userAssociationResponse?.value?.website_link=s.toString()
                    }
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
            })
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
    fun validationFields(etAny: EditText?):Boolean {
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

    /**
     * set un confirm
     */
    private fun setUnConfirm() {
        if(activity!=null) {
            btnYes?.background=
                ContextCompat.getDrawable(requireActivity(), R.drawable.unselected_type_bg)
            btnYes?.setTextColor(requireActivity().getColor(R.color.grey4))
            btnNo?.background=
                ContextCompat.getDrawable(requireActivity(), R.drawable.selected_type_bg_stage46)
            btnNo?.setTextColor(requireActivity().getColor(R.color.white))
            viewModel?.userAssociationResponse?.value?.have_section_46=false
        }
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
        //haveSection46=true
        viewModel?.userAssociationResponse?.value?.have_section_46=true
    }

    private fun setListener() {
        if(activity!=null) {

            viewModel?.isEditable?.observe(requireActivity(), Observer {
                if(it){
                    toggleEditReadOnlyStatus(true)
                }else{
                    toggleEditReadOnlyStatus(false)
                }
            })

            viewModel?.userAssociationResponse?.observe(requireActivity(), Observer {
                var userAssociationResponse=it
                if(userAssociationResponse!=null) {
                    fillUserFields(userAssociationResponse)
                }

            })
        }
    }

    private fun fillUserFields(userAssociationResponse: UserAssociationResponse) {
       val pos=getPositionBySelectedTypeAssociation()
        spTypeOrganization?.setSelection(pos)
        etAssociation?.setText(userAssociationResponse.organization_name)
        if(userAssociationResponse.have_section_46 == true){
            setConfirm()
        }else{
            setUnConfirm()
        }
        etContactPosition?.setText(userAssociationResponse.contact_position)
        etPNameContact?.setText(userAssociationResponse.contact_first_name)
        etFamilyName?.setText(userAssociationResponse.contact_last_name)
        etMobile?.setText(userAssociationResponse.phone)
        etEmail?.setText(userAssociationResponse.email)
        etAssociateAddress?.setText(userAssociationResponse.street)
        etNumAddress?.setText(userAssociationResponse.home_num)
        etCountry?.setText(userAssociationResponse.state)
        etCity?.setText(userAssociationResponse.city)
        etPostal?.setText(userAssociationResponse.zip_code)
        etAccountName?.setText(userAssociationResponse.bank_account_name)
        etAccountNum?.setText(userAssociationResponse.bank_account_num)
        etBranchName?.setText(userAssociationResponse.bank_branch_name)
        etBranchNum?.setText(userAssociationResponse.bank_branch_num)
        etBankNum?.setText(userAssociationResponse.bank_num)
        etUserName?.setText(userAssociationResponse.email)
        //etNumUsers?.setText(userAssociationResponse.number_of_users.toString())
        etPersonalAssociationLink?.setText(userAssociationResponse.website_link)
        if(activity!=null && ivAddGallery!=null) {
            Glide.with(requireActivity()).load(userAssociationResponse.image).optionalCenterCrop()
                //.transform(CircleCrop())
                //.override(200, 200)
                .into(ivAddGallery!!)
        }
    }


}