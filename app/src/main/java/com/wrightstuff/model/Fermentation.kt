package com.wrightstuff.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Fermentation {
    @SerializedName("temp")
    @Expose
    var temp: Temp? = null
}