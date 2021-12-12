package com.example.tgplayer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HTTPClientModule {

    @Provides
    @Singleton
    fun okhttpClient(): OkHttpClient {
        return OkHttpClient()
    }
}