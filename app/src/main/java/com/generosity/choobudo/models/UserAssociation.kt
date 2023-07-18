package com.generosity.choobudo.models

import androidx.core.text.isDigitsOnly
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

    @SerializedName("image")
    var image: String?=null


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


    /**
     * parse UserResponse to UserAssociation
     */
    fun parseAssociationUser(userResponse: UserAssociationResponse) {
        this.email = userResponse.email
        this.phone = userResponse.phone
        this.city = userResponse.city
        this.state = userResponse.state
        this.group_donations = userResponse.group_donations?.code_number
        this.contact_first_name = userResponse.contact_first_name
        this.contact_last_name = userResponse.contact_last_name
        this.organization_type = userResponse.organization_type?.code_number
        this.organization_name = userResponse.organization_name
        this.have_section_46 = userResponse.have_section_46
        this.contact_position = userResponse.contact_position
        this.street = userResponse.street
        this.home_num = userResponse.home_num
        this.postal_code = userResponse.zip_code
        this.bank_account_name = userResponse.bank_account_name
        if(userResponse.bank_account_num!=null
            && userResponse.bank_account_num.isDigitsOnly()) {
            this.bank_account_num=userResponse.bank_account_num.toInt()
        }
        if(userResponse.bank_num!=null
            && userResponse.bank_num.isDigitsOnly()) {
            this.bank_num=userResponse.bank_num.toInt()
        }
        this.website_link = userResponse.website_link
        //this.image = userResponse.image
        this.update_in_mail = userResponse.updates_in_mail
        this.update_in_sms = userResponse.updates_in_sms
        this.update_in_whatsapp = userResponse.updates_in_whatsapp
        this.organization_summary = userResponse.summary
        this.group_donation_abroad_uk_tormet = userResponse.group_donation_abroad_uk_tormet
        this.group_donation_abroad_account_in_jgive = userResponse.group_donation_abroad_account_in_jgive
        this.bank_branch_name = userResponse.bank_branch_name
        if(userResponse.bank_branch_num!=null
            && userResponse.bank_branch_num.isDigitsOnly()) {
            this.bank_branch_num=userResponse.bank_branch_num.toInt()
        }
    }

}