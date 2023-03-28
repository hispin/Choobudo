package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

/**
 * response of associations
 */
class AssociationsResponse(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("image") var image: String,
    @SerializedName("summary") var summary: String
)
