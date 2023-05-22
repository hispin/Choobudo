package com.generosity.choobudo.registration.fragment

import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.generosity.choobudo.R
import com.generosity.choobudo.common.common
import com.generosity.choobudo.registration.RegisterViewModel
import java.util.regex.Pattern

class RegSixthAssociateFragment : Fragment() {

    var tvTermOfUse: AppCompatTextView?=null
    var etUserName:EditText?=null
    var etPasswordNum:EditText?=null
    var etNumUsers:EditText?=null
    var etPersonalAssociationLink:EditText?=null
    var cbIConfirm:CheckBox?=null
    var cbIRead:CheckBox?=null
    private var viewModel: RegisterViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(requireActivity())[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_reg_sixth_associate, container, false)
        initView(view)
        viewModel?.setRegFirstValidate(false)
        makeLink()
        return view
    }

    private fun initView(view: View?) {
        tvTermOfUse=view?.findViewById(R.id.tvTermOfUse)
        etPasswordNum=view?.findViewById(R.id.etPasswordNum)
        etUserName=view?.findViewById(R.id.etUserName)
        etUserName?.setText(viewModel?.userAssociation?.email)
        etNumUsers=view?.findViewById(R.id.etNumUsers)
        tvTermOfUse=view?.findViewById(R.id.tvTermOfUse)
        etPersonalAssociationLink=view?.findViewById(R.id.etPersonalAssociationLink)
        cbIConfirm=view?.findViewById(R.id.cbIConfirm)
        cbIRead=view?.findViewById(R.id.cbIRead)
        etPasswordNum?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        etNumUsers?.onFocusChangeListener=View.OnFocusChangeListener { v, hasFocus ->
            validationFields()
        }
        cbIRead?.setOnCheckedChangeListener { buttonView, isChecked ->
            validationFields()
        }
        cbIConfirm?.setOnCheckedChangeListener { buttonView, isChecked ->
            validationFields()
        }
    }

    /**
     * make textview as link
     */
    private fun makeLink() {
        tvTermOfUse?.isClickable=true
        tvTermOfUse?.movementMethod=LinkMovementMethod.getInstance()
        val source=resources.getString(R.string.term_of_use)
        val text="<a href=''>$source </a>"
        tvTermOfUse?.text=Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        tvTermOfUse?.setOnClickListener {
            //TODO
        }
    }
    /**
     * fields validate
     */
    fun validationFields():Boolean{
        if (!TextUtils.isEmpty(etPasswordNum?.text)
            && common.isValidation(
                Pattern.compile(common.Constant.PASSWORD_VALIDATION),
                etPasswordNum?.text.toString()
            )
        ) {
            etPasswordNum?.setBackgroundResource(R.drawable.shape_field_fill)
            etPasswordNum?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        } else {
            etPasswordNum?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etPasswordNum?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        }

        if (TextUtils.isEmpty(etPersonalAssociationLink?.text)) {
            etPersonalAssociationLink?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etPersonalAssociationLink?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etPersonalAssociationLink?.setBackgroundResource(R.drawable.shape_field_fill)
            etPersonalAssociationLink?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }

        if (TextUtils.isEmpty(etNumUsers?.text)) {
            etNumUsers?.setBackgroundResource(R.drawable.shape_field_invalidate)
            etNumUsers?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_x, 0)
            viewModel?.setRegFirstValidate(false)
            return false
        } else {
            etNumUsers?.setBackgroundResource(R.drawable.shape_field_fill)
            etNumUsers?.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_vi_user, 0)
        }

        if(cbIRead?.isChecked == false){
            viewModel?.setRegFirstValidate(false)
            return false
        }

        if(cbIConfirm?.isChecked == false){
            viewModel?.setRegFirstValidate(false)
            return false
        }

        viewModel?.setRegFirstValidate(true)
        return true
    }

}