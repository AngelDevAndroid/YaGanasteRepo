package com.example.yaganaste.dbroom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yaganaste.data.BancsModel
import com.example.yaganaste.data.EntyBancsModel

// Data base
@Database(entities = [EntyBancsModel::class], version = 1, exportSchema = false)
//@TypeConverters(ConvertersImg::class)
abstract class BancsDB: RoomDatabase() {
    abstract fun productDao(): FavoriteDao
}