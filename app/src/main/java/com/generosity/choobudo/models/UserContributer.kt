package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

class UserContributer(
) : User()
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
    var organization_guid: String?=""
}