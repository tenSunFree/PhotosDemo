package com.example.photosdemo.common.remote

import com.example.photosdemo.room.model.RoomPhotosResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PixabayClient @Inject constructor(
    private val pixabayApi: PixabayApi
) {

    suspend fun searchPhotos(query: String, page: Int, perPage: Int): RoomPhotosResponse =
        pixabayApi.searchPhotos(query, page, perPage)
}