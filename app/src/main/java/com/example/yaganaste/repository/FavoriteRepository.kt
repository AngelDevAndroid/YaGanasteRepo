package com.example.yaganaste.repository

import com.example.yaganaste.data.BancsModel
import com.example.yaganaste.data.EntyBancsModel
import com.example.yaganaste.dbroom.FavoriteDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(val favorite: FavoriteDao?) {

    suspend fun addBancFav(banc: EntyBancsModel) {
        favorite?.addFavorite(banc)
    }

    fun getBancFav(): List<EntyBancsModel> {
        return favorite?.getAllFavorites()!!
    }
}