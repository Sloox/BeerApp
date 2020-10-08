package com.wrightstuff.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Hop {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("amount")
    @Expose
    var amount: Amount? = null

    @SerializedName("add")
    @Expose
    var add: String? = null

    @SerializedName("attribute")
    @Expose
    var attribute: String? = null
}