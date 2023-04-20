package com.generosity.choobudo.activities.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.generosity.choobudo.R

class MainScreenActivity : AppCompatActivity() {
    private var viewModel: MainScreenViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        viewModel=ViewModelProvider(this)[MainScreenViewModel::class.java]
        viewModel?.getWebSite()
    }
}