package com.generosity.choobudo.main

import android.os.Bundle
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.generosity.choobudo.R
import com.generosity.choobudo.activities.BaseActivity
import com.generosity.choobudo.associations.AssociationFragment
import com.generosity.choobudo.websites.WebsiteFragment

class MainScreenActivity : BaseActivity() {
    private var viewModel: MainScreenViewModel?=null
    private var llStores:LinearLayoutCompat?=null
    private var llAssociation:LinearLayoutCompat?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        llStores=findViewById(R.id.llStores)
        llStores?.setOnClickListener {
            val myFragment=WebsiteFragment()
            val t: FragmentTransaction=supportFragmentManager.beginTransaction()
            t.replace(R.id.frContainer, myFragment)
            t.addToBackStack(null)
            t.commit()
        }

        llAssociation=findViewById(R.id.llAssociation)
        llAssociation?.setOnClickListener {
            val myFragment=AssociationFragment()
            val t: FragmentTransaction=supportFragmentManager.beginTransaction()
            t.replace(R.id.frContainer, myFragment)
            t.addToBackStack(null)
            t.commit()
        }

        val myFragment=MainBoardFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment)
        t.addToBackStack(null)
        t.commit()

        viewModel=ViewModelProvider(this)[MainScreenViewModel::class.java]

    }
}