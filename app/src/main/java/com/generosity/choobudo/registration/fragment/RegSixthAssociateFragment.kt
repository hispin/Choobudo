package com.generosity.choobudo.registration.fragment

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.generosity.choobudo.R

class RegSixthAssociateFragment : Fragment() {

    var tvTermOfUse: AppCompatTextView?=null
    var etPasswordNum:EditText?=null
    var spNumUsers:Spinner?=null
    var etPersonalAssociationLink:EditText?=null
    var cbIConfirm:CheckBox?=null
    var cbIRead:CheckBox?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_reg_sixth_associate, container, false)
        initView(view)
        makeLink()
        return view
    }

    private fun initView(view: View?) {
        tvTermOfUse=view?.findViewById(R.id.tvTermOfUse)
        etPasswordNum=view?.findViewById(R.id.etPasswordNum)
        spNumUsers=view?.findViewById(R.id.spNumUsers)
        spNumUsers?.isEnabled=false
        tvTermOfUse=view?.findViewById(R.id.tvTermOfUse)
        etPersonalAssociationLink=view?.findViewById(R.id.etPersonalAssociationLink)
        cbIConfirm=view?.findViewById(R.id.cbIConfirm)
        cbIRead=view?.findViewById(R.id.cbIRead)
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

}