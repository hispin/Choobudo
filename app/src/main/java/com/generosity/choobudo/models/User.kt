package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

open class User {
    @SerializedName("email")
    var email: String=""

    @SerializedName("password")
    var password: String=""

    @SerializedName("phone")
    var phone: String?=null

    @SerializedName("city")
    var city: String?=null

    @SerializedName("state")
    var state: String=""

    @SerializedName("term_accepted")
    var term_accepted: Boolean?=null

    @SerializedName("group_donation")
    var group_donations: Int?=null

    @SerializedName("user_type")
    var user_type: Int?=null
}