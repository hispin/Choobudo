package com.generosity.choobudo.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.SearchAutoComplete
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.generosity.choobudo.models.WebsiteResponse
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainBoardFragment : Fragment() {

    private var adapter: SpecialsAdapter?=null
    private var on_boarding_view_pager: ViewPager2?=null
    private var viewModel: MainScreenViewModel?=null
    var recSpecialForYou: RecyclerView?=null
    private var searchStore: SearchView?=null
    private  var intfcCallBackFromMainBoard:IntfcCallBackFromMainBoard?=null

    interface IntfcCallBackFromMainBoard {
        fun sortWebsites()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        intfcCallBackFromMainBoard = context as IntfcCallBackFromMainBoard
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(com.generosity.choobudo.R.layout.fragment_main_board, container, false)
        on_boarding_view_pager = view.findViewById(com.generosity.choobudo.R.id.on_boarding_view_pager)
        recSpecialForYou = view.findViewById(com.generosity.choobudo.R.id.recSpecialForYou)

        loadBanners(view)

        if(activity!=null) {
            viewModel=ViewModelProvider(requireActivity())[MainScreenViewModel::class.java]
            setListener()
            viewModel?.getWebSite(true)
        }

        searchStore= view.findViewById(com.generosity.choobudo.R.id.searchStore1)

        return view
    }


    /**
     * set listener to view model
     */
    private fun setListener() {

        var website:ArrayList<WebsiteResponse>?=null


        //viewModel.userOrders.observe()
        viewModel?.userOrders?.observe(requireActivity(), Observer {
            val userOrders:ArrayList<WebsiteResponse> =it as ArrayList<WebsiteResponse>
            loadSpecialForYou(userOrders)
        })
        viewModel?.websiteResponse?.observe(requireActivity(), Observer {
            website =it as ArrayList<WebsiteResponse>
            val webSiteNames=ArrayList<String>()
            for(s1 in website!!){
                webSiteNames.add(s1.name)
            }
            val searchAutoComplete: SearchAutoComplete?=searchStore?.findViewById(androidx.appcompat.R.id.search_src_text)

            val newsAdapter: ArrayAdapter<String> = ArrayAdapter<kotlin.String>(requireActivity(), android.R.layout.simple_dropdown_item_1line, webSiteNames)

            if(searchAutoComplete!=null) {
                searchAutoComplete.setAdapter(newsAdapter)

                searchAutoComplete.onItemClickListener=
                    AdapterView.OnItemClickListener() { parent, view, position, id ->
                        searchAutoComplete.setText(parent.getItemAtPosition(position).toString())
                        viewModel?.getWebsiteByName(parent.getItemAtPosition(position).toString())
                        intfcCallBackFromMainBoard?.sortWebsites()
                    }
            }
        })

    }




    /**
     * load specialWebsites
     */
    private fun loadSpecialForYou(userOrders: ArrayList<WebsiteResponse>) {
        // set up the RecyclerView
        val horizontalLayoutManager=
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        recSpecialForYou?.layoutManager=horizontalLayoutManager
        adapter=SpecialsAdapter(userOrders)
        //adapter.setClickListener(this)
        recSpecialForYou?.adapter=adapter
    }

    /**
     * load banners
     */
    private fun loadBanners(view: View) {
        val numberOfScreens =
            resources.getStringArray(com.generosity.choobudo.R.array.banners_array).size
        val onBoardingAdapter = OnBoardingAdapter(requireActivity() as AppCompatActivity, numberOfScreens)
        on_boarding_view_pager?.offscreenPageLimit = 1
        on_boarding_view_pager?.adapter = onBoardingAdapter

        //for indicators dots
        if (on_boarding_view_pager != null) {
            val tabLayout =view.findViewById<TabLayout>(com.generosity.choobudo.R.id.tab_layout)
            val tabLayoutMediator = TabLayoutMediator(
                tabLayout, on_boarding_view_pager!!, true
            ) { tab, position -> }
            tabLayoutMediator.attach()
        }
    }


}