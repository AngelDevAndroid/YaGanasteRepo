package com.example.yaganaste.data

import com.google.gson.annotations.SerializedName

// Model
data class BancsModel(
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("age"         ) var age         : Int?    = null,
    @SerializedName("url"         ) var url         : String? = null,
    @SerializedName("bankName"    ) var bankName    : String? = null
)
