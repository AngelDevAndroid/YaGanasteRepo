package com.example.yaganaste.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// Model
@Entity(tableName = "FAVORITES")
data class EntyBancsModel(
    @PrimaryKey(autoGenerate = true)
    var idBanc: Int? = null,
    var bankName    : String? = null,
    var description : String? = null,
    var age         : Int?    = null,
    var url         : String? = null,
)
