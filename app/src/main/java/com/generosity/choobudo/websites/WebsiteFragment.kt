package com.generosity.choobudo.websites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.generosity.choobudo.common.RecyclerViewItemDecorator
import com.generosity.choobudo.common.common.Constant.IS_SORTED_KEY
import com.generosity.choobudo.main.MainScreenViewModel
import com.generosity.choobudo.models.WebsiteResponse


class WebsiteFragment : Fragment() {

    private var isSorted: Boolean?=false
    private var adapter: WebsiteAdapter?=null
    private var viewModel: MainScreenViewModel?=null
    var recWebsite: RecyclerView?=null

    private var intfcCallBackFromWebsite:IntfcCallBackFromWebsite?=null
    interface IntfcCallBackFromWebsite {
        fun onOpenWebStore(item: WebsiteResponse?,isScrollDown:Boolean)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        intfcCallBackFromWebsite = context as IntfcCallBackFromWebsite
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(com.generosity.choobudo.R.layout.fragment_website, container, false)
        recWebsite = view.findViewById(com.generosity.choobudo.R.id.recWebsite)


        isSorted=arguments?.getBoolean(IS_SORTED_KEY,false)

        if(activity!=null) {
            viewModel=ViewModelProvider(requireActivity())[MainScreenViewModel::class.java]
            setListener()
            viewModel?.getWebSite(false)
        }

        return view
    }

    private fun setListener() {
        viewModel?.websiteResponse?.observe(requireActivity(), Observer {
            val websites:ArrayList<WebsiteResponse> =it as ArrayList<WebsiteResponse>
            if(isSorted == true){
                viewModel?.sortedWebsite?.value?.let { it1 -> ArrayList(it1) }
                    ?.let { it2 -> loadWebsite(it2) }
            }else {
                loadWebsite(websites)
            }
        })
    }

    /**
     * load specialWebsites
     */
    private fun loadWebsite(websites: ArrayList<WebsiteResponse>) {
        // set up the RecyclerView
        val horizontalLayoutManager= GridLayoutManager(requireActivity(), 2)

        recWebsite?.layoutManager=horizontalLayoutManager

        recWebsite?.addItemDecoration(RecyclerViewItemDecorator(10))

        adapter=WebsiteAdapter(websites, object :WebsiteAdapter.WebsiteOnItemClickListener{
            override fun onWebsiteClick(item: WebsiteResponse?) {
                intfcCallBackFromWebsite?.onOpenWebStore(item,false)
            }
        })
        recWebsite?.adapter=adapter
    }

}