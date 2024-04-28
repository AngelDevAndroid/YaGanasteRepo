package com.example.yaganaste.dbroom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yaganaste.data.BancsModel
import com.example.yaganaste.data.EntyBancsModel

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(nProduct: EntyBancsModel)

    @Query("SELECT * FROM FAVORITES")
    fun getAllFavorites(): List<EntyBancsModel>
}