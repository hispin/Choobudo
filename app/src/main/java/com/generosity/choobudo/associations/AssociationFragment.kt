package com.generosity.choobudo.associations

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
import com.generosity.choobudo.models.AssociationsResponse

class AssociationFragment : Fragment() {
    private var adapter: AssociationAdapter?=null
    private var viewModel: MainScreenViewModel?=null
    var recAssociation: RecyclerView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_association, container, false)
        recAssociation = view.findViewById(R.id.recAssociation)


        if(activity!=null) {
            viewModel=ViewModelProvider(requireActivity())[MainScreenViewModel::class.java]
            setListener()
            viewModel?.getAssociations()
        }

        return view
    }

    private fun setListener() {
        //viewModel.userOrders.observe()
        viewModel?.associationsResponse?.observe(requireActivity(), Observer {
            val associations:ArrayList<AssociationsResponse> =it as ArrayList<AssociationsResponse>
            loadAssociations(associations)
        })
    }

    /**
     * load specialWebsites
     */
    private fun loadAssociations(associations: ArrayList<AssociationsResponse>) {
        // set up the RecyclerView
        val horizontalLayoutManager= GridLayoutManager(requireActivity(), 2)
        //LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        recAssociation?.layoutManager=horizontalLayoutManager
        adapter=AssociationAdapter(associations)
        //adapter.setClickListener(this)
        recAssociation?.adapter=adapter
    }
}