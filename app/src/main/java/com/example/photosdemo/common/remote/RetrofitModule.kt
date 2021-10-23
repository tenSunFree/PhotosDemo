package com.example.photosdemo.common.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class PixabayRetrofit

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    companion object {
        private const val PIXABAY_BASE_URL = "https://pixabay.com/api/"
        private val pixabayKey = Keys.getKey()
    }

    private fun buildOkHttpClient(key: String): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val httpUrl = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("key", key)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(httpUrl)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @PixabayRetrofit
    fun providePixabayOkHttpClient(): OkHttpClient {
        return buildOkHttpClient(pixabayKey ?: "")
    }

    @Provides
    @PixabayRetrofit
    fun providePixabayRetrofit(@PixabayRetrofit client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(PIXABAY_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}