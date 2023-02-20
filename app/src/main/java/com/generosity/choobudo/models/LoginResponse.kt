package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("token_key") var token_key: String,
    @SerializedName("token_type") var token_type: String,
    @SerializedName("expires_in") var expires_in: Double
) {
//    data class Data(
//        @SerializedName("Email") var email: String,
//        @SerializedName("id") var id: String,
//        @SerializedName("Id") var id2: Int,
//        @SerializedName("Name") var name: String,
//        @SerializedName("Token") var token: String
//    )
}
