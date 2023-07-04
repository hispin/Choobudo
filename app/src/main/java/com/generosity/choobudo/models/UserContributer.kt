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
    var birth_day: Int?=null

    @SerializedName("birth_month")
    var birth_month: Int?=null

    @SerializedName("birth_year")
    var birth_year: Int?=null

    @SerializedName("organization_guid")
    var organization_guid: String?=null

    /**
     * parse UserResponse to UserContributer
     */
    fun parseUser(userResponse:UserResponse) {
        this.first_name = userResponse.first_name
        this.last_name = userResponse.last_name
        this.organization_guid = userResponse.organization_unique_id
        val arr=userResponse.birthday.split("/")
        if(arr.size>2){
            birth_day = arr[0].toInt()
            birth_month= arr[1].toInt()
            birth_year = arr[2].toInt()
        }
        this.email = userResponse.email
        this.phone = userResponse.phone
        this.city = userResponse.city
        this.state = userResponse.state
        this.group_donations = userResponse.group_donations?.code_number
    }
}

