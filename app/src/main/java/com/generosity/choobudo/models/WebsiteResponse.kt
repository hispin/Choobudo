package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

class WebsiteResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("image") var image: String,
    @SerializedName("summary") var summary: String,
    @SerializedName("affiliate_link") var affiliate_link: String,
    @SerializedName("website_link") var website_link: String
)