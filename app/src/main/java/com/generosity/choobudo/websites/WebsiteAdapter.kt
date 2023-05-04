package com.generosity.choobudo.websites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.generosity.choobudo.R
import com.generosity.choobudo.models.WebsiteResponse

class WebsiteAdapter (private val mList: List<WebsiteResponse>) : RecyclerView.Adapter<WebsiteAdapter.ViewHolder>() {

    //private var myContext:Context?=null

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.item_website, parent, false)

        //myContext = parent.context

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel=mList[position]

        Glide.with(holder.imageView.context).load(ItemsViewModel.image).transform(CircleCrop())
            .override(200, 200).into(holder.imageView)

        // sets the text to the textview from our itemHolder class
        holder.textView.text=ItemsViewModel.name

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView=itemView.findViewById(R.id.ivWebsiteBanner)
        val textView: TextView=itemView.findViewById(R.id.tvWebsiteName)
    }
}