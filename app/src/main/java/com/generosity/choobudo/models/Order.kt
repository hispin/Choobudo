package com.generosity.choobudo.models

import com.google.gson.annotations.SerializedName

class Order (
@SerializedName("website_id") var website_id: Int,
@SerializedName("sum") var sum: Double,
@SerializedName("create_date") var create_date: String )