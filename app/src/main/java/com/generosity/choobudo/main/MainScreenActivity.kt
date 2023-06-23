package com.generosity.choobudo.main

import android.os.Bundle
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.generosity.choobudo.R
import com.generosity.choobudo.activities.BaseActivity
import com.generosity.choobudo.associations.AssociationFragment
import com.generosity.choobudo.common.common.Constant.IS_SORTED_KEY
import com.generosity.choobudo.main.myarea.MyAreaManagerFragment
import com.generosity.choobudo.models.WebsiteResponse
import com.generosity.choobudo.websites.WebsiteFragment
import com.generosity.choobudo.webview.WebViewFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainScreenActivity : BaseActivity(), WebsiteFragment.IntfcCallBackFromWebsite,
    MainBoardFragment.IntfcCallBackFromMainBoard {
    private var viewModel: MainScreenViewModel?=null
    private var llStores:LinearLayoutCompat?=null
    private var llAssociation:LinearLayoutCompat?=null
    private var llYulal:LinearLayoutCompat?=null
    private var llFaq:LinearLayoutCompat?=null
    private var llSaved:LinearLayoutCompat?=null
    private var llProfile:LinearLayoutCompat?=null
    private var mainBoard: FloatingActionButton?=null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        llStores=findViewById(R.id.llStores)
        llStores?.setOnClickListener {
            openWebsites(false)
        }

        llAssociation=findViewById(R.id.llAssociation)
        llAssociation?.setOnClickListener {
            val myFragment=AssociationFragment()
            val t: FragmentTransaction=supportFragmentManager.beginTransaction()
            t.replace(R.id.frContainer, myFragment)
            t.addToBackStack(null)
            t.commit()
        }

        llYulal=findViewById(R.id.llYulal)
        llYulal?.setOnClickListener {
            loadWebPage("https://www.choobudo.com/%D7%96%D7%95%D7%9B%D7%A8%D7%99%D7%9D-%D7%90%D7%AA-%D7%99%D7%95%D7%91%D7%9C/",true)
        }

        llFaq=findViewById(R.id.llFaq)
        llFaq?.setOnClickListener {
            loadWebPage("https://www.choobudo.com/%D7%A9%D7%90%D7%9C%D7%95%D7%AA-%D7%95%D7%AA%D7%A9%D7%95%D7%91%D7%95%D7%AA/",true)
        }

        llProfile=findViewById(R.id.llProfile)
        llProfile?.setOnClickListener {
            val myFragment=MyAreaManagerFragment()
            val t: FragmentTransaction=supportFragmentManager.beginTransaction()
            t.replace(R.id.frContainer, myFragment)
            t.addToBackStack(null)
            t.commit()
        }
        mainBoard=findViewById(R.id.mainBoard)
        mainBoard?.setOnClickListener {
            val myFragment=MainBoardFragment()
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

    /**
     * open websites
     */
    private fun openWebsites(isSorted:Boolean) {
        val myFragment=WebsiteFragment()
        val bundle=Bundle()
        bundle.putBoolean(IS_SORTED_KEY, isSorted)
        myFragment.arguments=bundle
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment)
        t.addToBackStack(null)
        t.commit()
    }

    override fun onOpenWebStore(item: WebsiteResponse?,isScrollDown:Boolean) {
        item?.affiliate_link?.let { loadWebPage(it,isScrollDown) }
    }

    /**
     * load web page by link url
     */
    fun loadWebPage(url:String,isScrollDown:Boolean){
        val myFragment=WebViewFragment.newInstance(url,isScrollDown)
        val t: FragmentTransaction=supportFragmentManager.beginTransaction()
        t.replace(R.id.frContainer, myFragment)
        t.addToBackStack(null)
        t.commit()
    }

    override fun sortWebsites() {
        openWebsites(true)
    }
}