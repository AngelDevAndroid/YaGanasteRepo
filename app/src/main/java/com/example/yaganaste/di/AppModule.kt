package com.example.yaganaste.di

import android.content.Context
import androidx.room.Room
import com.example.yaganaste.dbroom.BancsDB
import com.example.yaganaste.dbroom.FavoriteDao
import com.example.yaganaste.domine.CallApiRest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(retrofit: Retrofit): CallApiRest {
        return retrofit.create(CallApiRest::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(/*okHttpClient: OkHttpClient*/): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dev.obtenmas.com/")
            //.client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BancsDB {
        return Room.databaseBuilder(
            context,
            BancsDB::class.java,
            "banc_favorite_db"
        ).build()
    }

    @Provides
    fun provideFavDao(database: BancsDB): FavoriteDao {
        return database.productDao()
    }
}