package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

open class User
//(
//    @SerializedName("email") var email: String,
//    @SerializedName("password") var password: String,
//    @SerializedName("phone") var phone: String,
//    @SerializedName("city") var city: String,
//    @SerializedName("state") var state: String,
//    @SerializedName("term_accepted") var term_accepted: Boolean,
//    @SerializedName("group_donations") var group_donations: Int,
//    @SerializedName("user_type") var user_type: Int
//)
{
    @SerializedName("email")
    var email: String=""

    @SerializedName("password")
    var password: String=""

    @SerializedName("phone")
    var phone: String=""

    @SerializedName("city")
    var city: String=""

    @SerializedName("state")
    var state: String=""

    @SerializedName("term_accepted")
    var term_accepted: Boolean=false

    @SerializedName("group_donations")
    var group_donations: Int=0

    @SerializedName("user_type")
    var user_type: Int=0
}