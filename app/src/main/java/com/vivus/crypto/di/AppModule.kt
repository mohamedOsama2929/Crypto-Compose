package com.vivus.crypto.di

import com.vivus.crypto.common.Constants.BASE_URL
import com.vivus.crypto.data.remote.CoinPaprikaApi
import com.vivus.crypto.data.repository.CoinRepositoryImpl
import com.vivus.crypto.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(
        api: CoinPaprikaApi
    ): CoinRepository = CoinRepositoryImpl(api)


}