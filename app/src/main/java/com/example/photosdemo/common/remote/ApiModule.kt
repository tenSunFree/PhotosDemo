package com.example.photosdemo.common.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun providePixabayApi(@PixabayRetrofit retrofit: Retrofit): PixabayApi {
        return retrofit.create(PixabayApi::class.java)
    }
}