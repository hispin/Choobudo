package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName
//use for get user contribution
class UserResponse {
    @SerializedName("phone")
    var phone: String=""

    @SerializedName("email")
    var email: String=""

    @SerializedName("city")
    var city: String=""

    @SerializedName("state")
    var state: String=""

    //class
    @SerializedName("group_donations")
    var group_donations: Group_donations1?=null

    //class
    @SerializedName("user_type")
    var user_type: User_type1?=null

    //class
    @SerializedName("donations_sum")
    var donations_sum: Donation_sum?=null


    @SerializedName("first_name")
    var first_name: String=""

    @SerializedName("last_name")
    var last_name: String=""

    @SerializedName("organization_unique_id")
    var organization_unique_id: String=""

    @SerializedName("birthday")
    var birthday: String=""

    @SerializedName("orders")
    var orders: List<Order>? = null
}

class Group_donations1 {
    @SerializedName("title")
    var title: String=""

    @SerializedName("code_number")
    var code_number: Int=0
}

class User_type1 {
    @SerializedName("title")
    var title: String=""

    @SerializedName("code_numberl")
    var code_number: Int=0
}

class Donation_sum {
    @SerializedName("ever")
    var ever: Float=-1.0f

    @SerializedName("annual")
    var annual: Float=-1.0f

    @SerializedName("quarterly")
    var quarterly: Float=-1.0f

}
