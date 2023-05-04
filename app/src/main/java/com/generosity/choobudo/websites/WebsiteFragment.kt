package com.generosity.choobudo.websites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.generosity.choobudo.R
import com.generosity.choobudo.main.MainScreenViewModel
import com.generosity.choobudo.models.WebsiteResponse


class WebsiteFragment : Fragment() {

    private var adapter: WebsiteAdapter?=null
    private var viewModel: MainScreenViewModel?=null
    var recWebsite: RecyclerView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_website, container, false)
        recWebsite = view.findViewById(R.id.recWebsite)


        if(activity!=null) {
            viewModel=ViewModelProvider(requireActivity())[MainScreenViewModel::class.java]
            setListener()
            viewModel?.getWebSite(false)
        }

        return view
    }

    private fun setListener() {
        //viewModel.userOrders.observe()
        viewModel?.websiteResponse?.observe(requireActivity(), Observer {
            val websites:ArrayList<WebsiteResponse> =it as ArrayList<WebsiteResponse>
            loadWebsite(websites)
        })
    }

    /**
     * load specialWebsites
     */
    private fun loadWebsite(websites: ArrayList<WebsiteResponse>) {
        // set up the RecyclerView
        val horizontalLayoutManager= GridLayoutManager(requireActivity(), 2)
            //LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        recWebsite?.layoutManager=horizontalLayoutManager
        adapter=WebsiteAdapter(websites)
        //adapter.setClickListener(this)
        recWebsite?.adapter=adapter
    }

}