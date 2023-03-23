package com.generosity.choobudo.fragments

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.generosity.choobudo.R


class RegThirdFragment : Fragment() {

    private var tvTermOfUse: AppCompatTextView?=null
    var etDayBirth: EditText?=null
    var etMonthBirth: EditText?=null
    var etYearBirth: EditText?=null
    var etUser: EditText?=null
    var etPassword: EditText?=null
    var cbIRead: CheckBox?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_reg_third, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        tvTermOfUse=view?.findViewById(R.id.tvTermOfUse)
        makeLink()
        etDayBirth=view?.findViewById(R.id.etDayBirth)
        etMonthBirth=view?.findViewById(R.id.etMonthBirth)
        etYearBirth=view?.findViewById(R.id.etYearBirth)
        etUser=view?.findViewById(R.id.etUser)
        etPassword=view?.findViewById(R.id.etPassword)
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