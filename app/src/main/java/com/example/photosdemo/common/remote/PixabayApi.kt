package com.example.photosdemo.common.remote

import com.example.photosdemo.room.model.RoomPhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET(".")
    suspend fun searchPhotos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): RoomPhotosResponse
}