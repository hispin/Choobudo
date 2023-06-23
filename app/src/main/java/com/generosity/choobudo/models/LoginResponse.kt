package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("token_key") var token_key: String,
    @SerializedName("token_type") var token_type: String,
    @SerializedName("expires_in") var expires_in: Double
){
    @SerializedName("user_type")
    var user_type2: User_type2?=null
}
class User_type2 {
    @SerializedName("title")
    var title: String=""

    @SerializedName("code_number")
    var code_number: Int=0
}
