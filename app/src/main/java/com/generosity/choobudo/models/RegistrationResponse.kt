package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

class RegistrationResponse
//(
//    @SerializedName("token_key") var token_key: String,
//    @SerializedName("token_type") var token_type: String,
//    @SerializedName("expires_in") var expires_in: Double
//)

{
    @SerializedName("token_key")
    var token_key: String=""

    @SerializedName("token_type")
    var token_type: String=""

    @SerializedName("expires_in")
    var expires_in: Double=0.0

    @SerializedName("user_details")
    var user_details: User_details?=null
}

class User_details {
    @SerializedName("phone")
    var phone: String=""

    @SerializedName("email")
    var email: String=""

    @SerializedName("city")
    var city: String=""

    @SerializedName("state")
    var state: String=""

    @SerializedName("Token")
    var token: String=""

    //class
    @SerializedName("group_donations")
    var group_donations: Group_donations?=null

    //class
    @SerializedName("user_type")
    var user_type: User_type?=null

    @SerializedName("first_name")
    var first_name: String=""

    @SerializedName("last_name")
    var last_name: String=""

    @SerializedName("organization_unique_id")
    var organization_unique_id: String=""

    @SerializedName("birthday")
    var birthday: String=""
}

class Group_donations {
    @SerializedName("title")
    var title: String=""

    @SerializedName("code_number")
    var code_number: Int=0
}

class User_type {
    @SerializedName("title")
    var title: String=""

    @SerializedName("code_numberl")
    var code_number: Int=0
}


//@SerializedName("orders") var orders: []
//    }
//}

