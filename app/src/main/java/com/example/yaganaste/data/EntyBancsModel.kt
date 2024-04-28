package com.example.yaganaste.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// Model
@Entity(tableName = "FAVORITES")
data class EntyBancsModel(
    @PrimaryKey(autoGenerate = true)
    val idBanc: Int? = null,
    val bankName    : String? = null,
    val description : String? = null,
    val age         : Int?    = null,
    val url         : String? = null,
)
