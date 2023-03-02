package com.generosity.choobudo.activities

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.generosity.choobudo.R
import com.generosity.choobudo.fragments.RegFirstFragment
import kotlinx.android.synthetic.main.activity_registration.*


class RegisterActivity : BaseActivity() {

    enum class TypeUser {
        contributing, association
    }

    var type=TypeUser.contributing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        initViews()
        showDefaultStage()
    }

    /**
     * show default fragment
     */
    private fun showDefaultStage() {
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, RegFirstFragment())
        t.commit()
    }

    private fun initViews() {
        btnAssociation.setOnClickListener {
            setAssociatingStatus()
            type=TypeUser.association
        }
        btnContributing.setOnClickListener {
            setContributingStatus()
            type=TypeUser.contributing
        }

        makeLink()

    }

    /**
     * make textview as link
     */
    private fun makeLink() {
        btnConnectFromHere.isClickable=true
        btnConnectFromHere.movementMethod=LinkMovementMethod.getInstance()
        val source=resources.getString(R.string.connect_from_here)
        val text="<a href=''>$source </a>"
        btnConnectFromHere.text=Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        btnConnectFromHere.setOnClickListener {
            //TODO
        }
    }

    /**
     * set contributing status
     */
    private fun setContributingStatus() {
        btnAssociation.background=ContextCompat.getDrawable(this, R.drawable.unselected_type_bg)
        btnAssociation.setTextColor(getColor(R.color.grey4))
        btnContributing.background=ContextCompat.getDrawable(this, R.drawable.selected_type_bg)
        btnContributing.setTextColor(getColor(R.color.white))
    }

    /**
     * set association status
     */
    private fun setAssociatingStatus() {
        btnAssociation.background=ContextCompat.getDrawable(this, R.drawable.selected_type_bg)
        btnAssociation.setTextColor(getColor(R.color.white))
        btnContributing.background=ContextCompat.getDrawable(this, R.drawable.unselected_type_bg)
        btnContributing.setTextColor(getColor(R.color.grey4))
    }
}