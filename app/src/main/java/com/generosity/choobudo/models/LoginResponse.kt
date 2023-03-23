package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("token_key") var token_key: String,
    @SerializedName("token_type") var token_type: String,
    @SerializedName("expires_in") var expires_in: Double
)
