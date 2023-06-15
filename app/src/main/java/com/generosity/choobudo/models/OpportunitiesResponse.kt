package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

class OpportunitiesResponse (
    @SerializedName("name") var name: String,
    @SerializedName("items") var items: List<Opportunity>
)

class Opportunity(
    @SerializedName("name") var name: String,
    @SerializedName("image") var image: String,
    @SerializedName("link") var link: String,
    @SerializedName("text") var text: String,
    @SerializedName("website") var website: Website?=null
)

class Website(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("website_link") var website_link: String
    )

