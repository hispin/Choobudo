package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName("email") var email: String, @SerializedName("password") var password: String
) {
    //@SerializedName("email") var email: String?=null
    //@SerializedName("password") var password: String?=null
}