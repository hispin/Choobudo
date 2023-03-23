package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

class UserContributer(
//                       @SerializedName("first_name") var first_name: String,
//                       @SerializedName("last_name") var last_name: String,//
//                       @SerializedName("birth_day") var birth_day: Int,
//                       @SerializedName("birth_month") var birth_month: Int,
//                       @SerializedName("birth_year") var birth_year: Int,
//                       @SerializedName("organization_guid") var organization_guid: String,
//                       email: String, password: String, phone: String, city: String, state: String,
//                       term_accepted: Boolean, group_donations: Int, user_type: Int


) : User()//email, password, phone, city, state, term_accepted, group_donations, user_type)
{
    @SerializedName("first_name")
    var first_name: String=""

    @SerializedName("last_name")
    var last_name: String=""

    @SerializedName("birth_day")
    var birth_day: Int=0

    @SerializedName("birth_month")
    var birth_month: Int=0

    @SerializedName("birth_year")
    var birth_year: Int=0

    @SerializedName("organization_guid")
    var organization_guid: String=""
}