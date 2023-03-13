package com.generosity.choobudo.activities

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.generosity.choobudo.R
import com.generosity.choobudo.fragments.*
import kotlinx.android.synthetic.main.activity_registration.*


class RegisterActivity : BaseActivity() {
    //type of user
    enum class TypeUser {
        contributing, association
    }

    //stages of registration of contributer
    enum class StageContributer {
        one, two, three
    }

    //stages of registration of contributer
    enum class StageAssociate {
        one, two, three, four, five
    }

    var type=TypeUser.contributing
    var typeContributer=StageContributer.one
    var typeAssociate=StageAssociate.one

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        initViews()
        showFirstStage()
    }

    /**
     * show default fragment
     */
    private fun showFirstStage() {
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, RegFirstFragment())
        t.commit()
    }

    /**
     * show second fragment
     */
    private fun showSecondStage() {
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, RegSecondFragment())
        t.commit()
    }

    /**
     * show third stage
     */
    private fun showThirdStage() {
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, RegThirdFragment())
        t.commit()
    }

    /**
     * show association first stage
     */
    private fun showAssociateFirstStage() {
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, RegFirstAssociateFragment())
        t.commit()
    }

    /**
     * show association second stage
     */
    private fun showAssociateSecondStage() {
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, RegSecondAssociateFragment())
        t.commit()
    }

    private fun initViews() {
        btnAssociation.setOnClickListener {
            setAssociatingStatus()
            type=TypeUser.association
            showAssociateFirstStage()
        }
        btnContributing.setOnClickListener {
            setContributingStatus()
            type=TypeUser.contributing
            showFirstStage()
        }

        makeLink()

        btnNextReg.setOnClickListener {
            if (type == TypeUser.contributing) {
                when (typeContributer) {
                    StageContributer.one -> {
                        showSecondStage()
                        typeContributer=StageContributer.two
                        llTabsContainer.visibility=View.GONE
                        btnLongContributer.text=resources.getString(R.string.contributing_user)
                        btnLongContributer.visibility=View.VISIBLE
                        btnNextReg.text=resources.getString(R.string.next)
                    }

                    StageContributer.two -> {
                        showThirdStage()
                        typeContributer=StageContributer.three
                        llTabsContainer.visibility=View.GONE
                        btnLongContributer.text=resources.getString(R.string.contributing_user)
                        btnLongContributer.visibility=View.VISIBLE
                        btnNextReg.text=resources.getString(R.string.registration)
                    }
                    StageContributer.three -> {

                    }
                    else -> {}
                }
            } else if (type == TypeUser.association) {
                when (typeAssociate) {
                    StageAssociate.one -> {
                        showAssociateSecondStage()
                        typeAssociate=StageAssociate.two
                        llTabsContainer.visibility=View.GONE
                        btnLongContributer.text=resources.getString(R.string.association)
                        btnLongContributer.visibility=View.VISIBLE
                        btnNextReg.text=resources.getString(R.string.next)
                    }

                    StageAssociate.two -> {
                    }
                    StageAssociate.three -> {

                    }
                    StageAssociate.four -> {

                    }
                    else -> {}
                }
            }
        }

        ivBack.setOnClickListener {
            if (type == TypeUser.contributing) {
                when (typeContributer) {
                    StageContributer.two -> {
                        showFirstStage()
                        typeContributer=StageContributer.one
                        llTabsContainer.visibility=View.VISIBLE
                        btnLongContributer.visibility=View.GONE
                        btnNextReg.text=resources.getString(R.string.next)
                    }

                    StageContributer.three -> {
                        showSecondStage()
                        typeContributer=StageContributer.two
                        llTabsContainer.visibility=View.GONE
                        btnLongContributer.visibility=View.VISIBLE
                        btnNextReg.text=resources.getString(R.string.next)
                    }
                    StageContributer.one -> {

                    }
                    else -> {}
                }
            }
        }
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