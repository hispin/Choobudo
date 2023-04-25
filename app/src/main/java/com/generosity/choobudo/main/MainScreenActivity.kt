package com.generosity.choobudo.main

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.generosity.choobudo.R
import com.generosity.choobudo.activities.BaseActivity

class MainScreenActivity : BaseActivity() {
    private var viewModel: MainScreenViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        val myFragment=MainBoardFragment()
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment)
        t.addToBackStack(null)
        t.commit()

        viewModel=ViewModelProvider(this)[MainScreenViewModel::class.java]
        viewModel?.getWebSite()
    }
}