package com.generosity.choobudo.activities.registration

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.generosity.choobudo.R
import com.generosity.choobudo.activities.BaseActivity
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

    private var myFragment: Fragment?=null
    var type=TypeUser.contributing
    var typeContributer=StageContributer.one
    var typeAssociate=StageAssociate.one
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        initViews()
        setListener()
        showFirstStage()
    }

    private fun setListener() {
        viewModel.isSuccess?.observe(this, Observer {
            if (it) {
                Toast.makeText(
                    this@RegisterActivity,
                    resources.getString(R.string.success_msg),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    /**
     * show default fragment
     */
    private fun showFirstStage() {
        myFragment=RegFirstFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegFirstFragment)
        t.commit()
    }

    /**
     * show second fragment
     */
    private fun showSecondStage() {
        myFragment=RegSecondFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegSecondFragment)
        t.commit()
    }

    /**
     * show third stage
     */
    private fun showThirdStage() {
        myFragment=RegThirdFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegThirdFragment)
        t.commit()
    }

    /**
     * show association first stage
     */
    private fun showAssociateFirstStage() {
        myFragment=RegFirstAssociateFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegFirstAssociateFragment)
        t.commit()
    }

    /**
     * show association second stage
     */
    private fun showAssociateSecondStage() {
        myFragment=RegSecondAssociateFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegSecondAssociateFragment)
        t.commit()
    }

    /**
     * show association third stage
     */
    private fun showAssociateThirdStage() {
        myFragment=RegThirdAssociateFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegThirdAssociateFragment)
        t.commit()
    }

    /**
     * show association forth stage
     */
    private fun showAssociateForthStage() {
        myFragment=RegForthAssociateFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegForthAssociateFragment)
        t.commit()
    }

    /**
     * show association fifth stage
     */
    private fun showAssociateFifthStage() {
        myFragment=RegFifthAssociateFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegFifthAssociateFragment)
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
                        if (myFragment is RegFirstFragment) {
                            viewModel.setContributerStage1(
                                (myFragment as RegFirstFragment).etUserPName?.text.toString(),
                                (myFragment as RegFirstFragment).etUserFName?.text.toString(),
                                "8e8b988a-8af9-4383-a410-192c01f552a0"
                            )
                        }
                        showSecondStage()
                        typeContributer=StageContributer.two
                        configureContributerUI()
                        btnNextReg.text=resources.getString(R.string.next)
                    }

                    StageContributer.two -> {
                        if (myFragment is RegSecondFragment) {
                            viewModel.setContributerStage2(
                                (myFragment as RegSecondFragment).etTelNum?.text.toString(),
                                (myFragment as RegSecondFragment).etEmail?.text.toString(),
                                (myFragment as RegSecondFragment).etCity?.text.toString(),
                                (myFragment as RegSecondFragment).etCountry?.text.toString()
                            )
                        }
                        showThirdStage()
                        typeContributer=StageContributer.three
                        configureContributerUI()
                        btnNextReg.text=resources.getString(R.string.registration)
                    }
                    StageContributer.three -> {
                        if (myFragment is RegThirdFragment) {
                            viewModel.setContributerStage3(
                                (myFragment as RegThirdFragment).etDayBirth?.text.toString(),
                                (myFragment as RegThirdFragment).etMonthBirth?.text.toString(),
                                (myFragment as RegThirdFragment).etYearBirth?.text.toString(),
                                (myFragment as RegThirdFragment).etPassword?.text.toString(),
                                (myFragment as RegThirdFragment).cbIRead?.isChecked == true
                            )
                        }
                        viewModel.register()
                    }
                    else -> {}
                }
            } else if (type == TypeUser.association) {
                when (typeAssociate) {
                    StageAssociate.one -> {
                        showAssociateSecondStage()
                        typeAssociate=StageAssociate.two
                        configureAssociationUI()
                        btnNextReg.text=resources.getString(R.string.next)
                    }

                    StageAssociate.two -> {
                        showAssociateThirdStage()
                        typeAssociate=StageAssociate.three
                        configureAssociationUI()
                        btnNextReg.text=resources.getString(R.string.next)
                    }
                    StageAssociate.three -> {
                        showAssociateForthStage()
                        typeAssociate=StageAssociate.four
                        configureAssociationUI()
                        btnNextReg.text=resources.getString(R.string.next)
                    }
                    StageAssociate.four -> {
                        showAssociateFifthStage()
                        typeAssociate=StageAssociate.five
                        configureAssociationUI()
                        btnNextReg.text=resources.getString(R.string.registration)
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
                        configurePrevToOneUI()

                    }

                    StageContributer.three -> {
                        showSecondStage()
                        typeContributer=StageContributer.two
                        configurePrevUI()
                    }
                    StageContributer.one -> {

                    }
                    else -> {}
                }
            } else if (type == TypeUser.association) {
                when (typeAssociate) {
                    StageAssociate.five -> {
                        showAssociateForthStage()
                        typeAssociate=StageAssociate.four
                        configurePrevUI()
                    }

                    StageAssociate.four -> {
                        showAssociateThirdStage()
                        typeAssociate=StageAssociate.three
                        configurePrevUI()
                    }
                    StageAssociate.three -> {
                        showAssociateSecondStage()
                        typeAssociate=StageAssociate.two
                        configurePrevUI()
                    }
                    StageAssociate.two -> {
                        showAssociateFirstStage()
                        typeAssociate=StageAssociate.one
                        configurePrevToOneUI()
                    }
                    else -> {}
                }
            }
        }
    }

    /**
     * configure previous UI
     */
    private fun configurePrevToOneUI() {
        llTabsContainer.visibility=View.VISIBLE
        btnLongContributer.visibility=View.GONE
        btnNextReg.text=resources.getString(R.string.next)
    }

    private fun configurePrevUI() {
        llTabsContainer.visibility=View.GONE
        btnLongContributer.visibility=View.VISIBLE
        btnNextReg.text=resources.getString(R.string.next)
    }

    /**
     * configure association UI
     */
    private fun configureAssociationUI() {
        llTabsContainer.visibility=View.GONE
        btnLongContributer.text=resources.getString(R.string.association)
        btnLongContributer.visibility=View.VISIBLE
    }

    /**
     * configure contributer UI
     */
    private fun configureContributerUI() {
        llTabsContainer.visibility=View.GONE
        btnLongContributer.text=resources.getString(R.string.contributing_user)
        btnLongContributer.visibility=View.VISIBLE
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