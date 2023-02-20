package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("phone") var phone: String,
    @SerializedName("email") var email: String,
    @SerializedName("image") var image: String,
    @SerializedName("city") var city: String,
    @SerializedName("state") var state: String,
    @SerializedName("user_reg_type") var user_reg_type: Int,
    @SerializedName("birthday_date_yyyyMMdd") var birthday_date_yyyyMMdd: String,
    @SerializedName("group_donations") var group_donations: Int,

    )