package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

class UserAssociation (
) : User(){

    @SerializedName("organization_type")
    var organization_type: Int?=null

    @SerializedName("organization_name")
    var organization_name: String?=null

    @SerializedName("have_section_46")
    var have_section_46: Boolean?=null

    @SerializedName("contact_first_name")
    var contact_first_name: String?=null

    @SerializedName("contact_last_name")
    var contact_last_name: String?=null

    @SerializedName("contact_position")
    var contact_position: String?=null

    @SerializedName("street")
    var street: String?=null

    @SerializedName("home_num")
    var home_num: String?=null

    @SerializedName("postal_code")
    var postal_code: String?=null

    @SerializedName("bank_account_name")
    var bank_account_name: String?=null

    @SerializedName("bank_account_num")
    var bank_account_num: Int?=null

    @SerializedName("bank_branch_name")
    var bank_branch_name: String?=null

    @SerializedName("bank_branch_num")
    var bank_branch_num: Int?=null

    @SerializedName("bank_num")
    var bank_num: Int?=null

    @SerializedName("website_link")
    var website_link: String?=null


    @SerializedName("update_in_mail")
    var update_in_mail: Boolean?=null

    @SerializedName("update_in_sms")
    var update_in_sms: Boolean?=null

    @SerializedName("update_in_whatsapp")
    var update_in_whatsapp: Boolean?=null

    @SerializedName("organization_summary")
    var organization_summary: String?=null

    @SerializedName("group_donation_abroad_uk_tormet")
    var group_donation_abroad_uk_tormet: Boolean?=null

    @SerializedName("group_donation_abroad_account_in_jgive")
    var group_donation_abroad_account_in_jgive: Boolean?=null


//    @SerializedName("image")
//    var image: ?=null

}