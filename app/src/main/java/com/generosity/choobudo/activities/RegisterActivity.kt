package com.generosity.choobudo.activities

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import com.generosity.choobudo.R
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
    }

    private fun setContributingStatus() {
        val gd=GradientDrawable()

    }

    private fun setAssociatingStatus() {
        TODO("Not yet implemented")
    }
}