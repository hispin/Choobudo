package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

class RegistrationAssociationResponse {

        @SerializedName("token_key")
        var token_key: String=""

        @SerializedName("token_type")
        var token_type: String=""

        @SerializedName("expires_in")
        var expires_in: Double=0.0

        @SerializedName("user_details")
        var user_details: User_details1?=null
 }

class User_details1 {
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
        var group_donations: Group_donations?=null

        //class
        @SerializedName("user_type")
        var user_type: User_type?=null

        @SerializedName("organization_name")
        var organization_name: String=""

        @SerializedName("summary")
        var summary: String=""

        @SerializedName("website_link")
        var website_link: String=""

        @SerializedName("group_donation_abroad_uk_tormet")
        var group_donation_abroad_uk_tormet: Boolean?=null

        @SerializedName("group_donation_abroad_account_in_jgive")
        var group_donation_abroad_account_in_jgive: Boolean?=null

        @SerializedName("contact_first_name")
        var contact_first_name: String=""

        @SerializedName("contact_last_name")
        var contact_last_name: String=""


        @SerializedName("have_section_46")
        var have_section_46: Boolean?=null

        @SerializedName("contact_position")
        var contact_position: String=""

        @SerializedName("bank_account_name")
        var bank_account_name: String=""

        @SerializedName("bank_account_num")
        var bank_account_num: String=""

        @SerializedName("bank_branch_name")
        var bank_branch_name: String=""

        @SerializedName("bank_branch_num")
        var bank_branch_num: String=""

        @SerializedName("bank_num")
        var bank_num: String=""

        @SerializedName("updates_in_sms")
        var updates_in_sms: Boolean?=null

        @SerializedName("updates_in_whatsapp")
        var updates_in_whatsapp: Boolean?=null

        @SerializedName("updates_in_mail")
        var updates_in_mail: Boolean?=null

        @SerializedName("organization_type")
        var organization_type: Organization_type?=null

        @SerializedName("number_of_users")
        var number_of_users: Int?=null

        @SerializedName("street")
        var street: String=""

        @SerializedName("home_num")
        var home_num: String=""

        @SerializedName("zip_code")
        var zip_code :String=""


}

    class Organization_type {
        @SerializedName("title")
        var title: String=""

        @SerializedName("code_number")
        var code_number: Int=0
    }




