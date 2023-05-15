package com.generosity.choobudo.registration

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
import com.generosity.choobudo.common.common.Companion.openLogin
import com.generosity.choobudo.models.UserAssociation
import com.generosity.choobudo.models.UserContributer
import com.generosity.choobudo.registration.fragment.*
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
        one, two, three, four, five,six
    }
    private var isAction=false
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
            if (isAction) {
                if (it) {
                    Toast.makeText(
                        this@RegisterActivity,
                        resources.getString(R.string.success_msg),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        resources.getString(R.string.failed_msg),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        //listener to first registration validation
        viewModel.isRegFirstValidate?.observe(this, Observer {
                if (it) {
                    btnNextReg.setBackgroundResource(R.drawable.registration_next)
                    btnNextReg.isEnabled=true
                } else {
                    btnNextReg.setBackgroundResource(R.drawable.registration_next_disable)
                    btnNextReg.isEnabled=false
                }
        })

        viewModel.isEmailAlreadyExist?.observe(this, Observer {
            if(isAction) {
                isAction=false
                (myFragment as RegSecondAssociateFragment).emailProgressBar?.visibility=View.GONE
                if (it) {
                    Toast.makeText(
                        this@RegisterActivity,
                        resources.getString(R.string.email_already_exist),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (type == TypeUser.association) {
                        if (myFragment is RegSecondAssociateFragment) {
                            viewModel.setAssociationStage2(
                                (myFragment as RegSecondAssociateFragment).etPNameContact?.text.toString(),
                                (myFragment as RegSecondAssociateFragment).etFamilyName?.text.toString(),
                                (myFragment as RegSecondAssociateFragment).etMobile?.text.toString(),
                                (myFragment as RegSecondAssociateFragment).etEmail?.text.toString()
                            )
                        }
                        showAssociateThirdStage()
                        typeAssociate=StageAssociate.three
                        configureAssociationUI()
                        btnNextReg.text=resources.getString(R.string.next)

                    }else{
                        //go to third wizard in registration
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
                }
            }
        })
    }

    /**
     * show default fragment
     */
    private fun showFirstStage() {
        myFragment=RegFirstFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegFirstFragment,"RegFirstFragment")
        t.addToBackStack(null)
        t.commit()
    }

    /**
     * show second fragment
     */
    private fun showSecondStage() {
        myFragment=RegSecondFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegSecondFragment,"RegSecondFragment")
        t.addToBackStack(null)
        t.commit()
    }

    /**
     * show third stage
     */
    private fun showThirdStage() {
        myFragment=RegThirdFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegThirdFragment,"RegThirdFragment")
        t.addToBackStack(null)
        t.commit()
    }

    /**
     * show association first stage
     */
    private fun showAssociateFirstStage() {
        myFragment=RegFirstAssociateFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegFirstAssociateFragment)
        t.addToBackStack(null)
        t.commit()
    }

    /**
     * show association second stage
     */
    private fun showAssociateSecondStage() {
        myFragment=RegSecondAssociateFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegSecondAssociateFragment)
        t.addToBackStack(null)
        t.commit()
    }

    /**
     * show association third stage
     */
    private fun showAssociateThirdStage() {
        myFragment=RegThirdAssociateFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegThirdAssociateFragment)
        t.addToBackStack(null)
        t.commit()
    }

    /**
     * show association forth stage
     */
    private fun showAssociateForthStage() {
        myFragment=RegForthAssociateFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegForthAssociateFragment)
        t.addToBackStack(null)
        t.commit()
    }

    /**
     * show association fifth stage
     */
    private fun showAssociateFifthStage() {
        myFragment=RegfifthAssociateFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegfifthAssociateFragment)
        t.addToBackStack(null)
        t.commit()
    }

    /**
     * show association sixth stage
     */
    private fun showAssociateSixthStage() {
        myFragment=RegSixthAssociateFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment as RegSixthAssociateFragment)
        t.addToBackStack(null)
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
                                (myFragment as RegFirstFragment).getSelectedAssociation()//"8e8b988a-8af9-4383-a410-192c01f552a0"
                            )
                        }
                        showSecondStage()
                        typeContributer=StageContributer.two
                        configureContributerUI()
                        viewModel.setRegFirstValidate(false)
                        btnNextReg.text=resources.getString(R.string.next)
                    }

                    StageContributer.two -> {
                        val userContributer= UserContributer()
                        userContributer.email=(myFragment as RegSecondFragment).etEmail?.text.toString()
                        isAction=true
                        viewModel.checkEmailContributer(userContributer)
                        (myFragment as RegSecondFragment).emailProgressBar?.visibility=View.VISIBLE
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
                        isAction=true
                        viewModel.registerContributer()
                    }
                    else -> {}
                }
            } else if (type == TypeUser.association) {
                when (typeAssociate) {
                    StageAssociate.one -> {
                        if (myFragment is RegFirstAssociateFragment) {
                            viewModel.setAssociationStage1(
                                (myFragment as RegFirstAssociateFragment).getSelectedTypeAssociation(),
                                (myFragment as RegFirstAssociateFragment).etAssociationName?.text.toString(),
                                (myFragment as RegFirstAssociateFragment).haveSection46,
                                (myFragment as RegFirstAssociateFragment).etContactPosition?.text.toString()//"8e8b988a-8af9-4383-a410-192c01f552a0"
                            )
                        }
                        showAssociateSecondStage()
                        typeAssociate=StageAssociate.two
                        configureAssociationUI()
                        btnNextReg.text=resources.getString(R.string.next)
                    }

                    StageAssociate.two -> {
                        val userAssociation = UserAssociation()
                        userAssociation.email=(myFragment as RegSecondAssociateFragment).etEmail?.text.toString()
                        isAction=true
                        viewModel.checkEmailContributer(userAssociation)
                        (myFragment as RegSecondAssociateFragment).emailProgressBar?.visibility=View.VISIBLE
                    }
                    StageAssociate.three -> {
                        if (myFragment is RegThirdAssociateFragment) {
                            viewModel.setAssociationStage3(
                                (myFragment as RegThirdAssociateFragment).etAssociateAddress?.text.toString(),
                                (myFragment as RegThirdAssociateFragment).etNumAddress?.text.toString(),
                                (myFragment as RegThirdAssociateFragment).etCity?.text.toString(),
                                (myFragment as RegThirdAssociateFragment).etCountry?.text.toString(),//"8e8b988a-8af9-4383-a410-192c01f552a0"
                                (myFragment as RegThirdAssociateFragment).etPostal?.text.toString()//"8e8b988a-8af9-4383-a410-192c01f552a0"
                            )
                        }
                        showAssociateForthStage()
                        typeAssociate=StageAssociate.four
                        configureAssociationUI()
                        btnNextReg.text=resources.getString(R.string.next)
                    }
                    StageAssociate.four -> {
                        if (myFragment is RegForthAssociateFragment) {
                            viewModel.setAssociationStage4(
                                (myFragment as RegForthAssociateFragment).etAccountName?.text.toString(),
                                (myFragment as RegForthAssociateFragment).etAccountNum?.text.toString(),
                                (myFragment as RegForthAssociateFragment).etBranchName?.text.toString(),
                                (myFragment as RegForthAssociateFragment).etBranchNum?.text.toString(),//"8e8b988a-8af9-4383-a410-192c01f552a0"
                                (myFragment as RegForthAssociateFragment).etBankNum?.text.toString()//"8e8b988a-8af9-4383-a410-192c01f552a0"
                            )
                        }
                        showAssociateFifthStage()
                        typeAssociate=StageAssociate.five
                        configureAssociationUI()
                        btnNextReg.text=resources.getString(R.string.next)
                    }
                    StageAssociate.five -> {
//                        if (myFragment is RegForthAssociateFragment) {
//                            viewModel.setAssociationStage4(
//                                (myFragment as RegForthAssociateFragment).etAccountName?.text.toString(),
//                                (myFragment as RegForthAssociateFragment).etAccountNum?.text.toString(),
//                                (myFragment as RegForthAssociateFragment).etBranchName?.text.toString(),
//                                (myFragment as RegForthAssociateFragment).etBranchNum?.text.toString(),//"8e8b988a-8af9-4383-a410-192c01f552a0"
//                                (myFragment as RegForthAssociateFragment).etBankNum?.text.toString()//"8e8b988a-8af9-4383-a410-192c01f552a0"
//                            )
//                        }
                        showAssociateSixthStage()
                        typeAssociate=StageAssociate.six
                        configureAssociationUI()
                        btnNextReg.text=resources.getString(R.string.registration)
                    }
                    StageAssociate.six -> {
                        if (myFragment is RegSixthAssociateFragment) {
                            viewModel.setAssociationStage6(
                                (myFragment as RegSixthAssociateFragment).etPasswordNum?.text.toString(),
                                (myFragment as RegSixthAssociateFragment).etPersonalAssociationLink?.text.toString(),
                                (myFragment as RegSixthAssociateFragment).cbIConfirm?.isChecked,
                                (myFragment as RegSixthAssociateFragment).cbIRead?.isChecked
                            )
                            isAction=true
                            viewModel.registerAssociation()
                        }
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
                    else -> {
                        onBackPressedDispatcher.onBackPressed()
                    }
                }
            } else if (type == TypeUser.association) {
                when (typeAssociate) {

                    StageAssociate.six -> {
                        showAssociateFifthStage()
                        typeAssociate=StageAssociate.five
                        configurePrevUI()
                    }

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
                    else -> {
                        onBackPressedDispatcher.onBackPressed()
                    }
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
            openLogin(this)
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