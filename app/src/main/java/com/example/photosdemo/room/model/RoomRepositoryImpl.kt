package com.example.photosdemo.room.model

import com.example.photosdemo.common.remote.ApiClient
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient
) : RoomRepository {

    override suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int
    ): List<RoomPhotos> {
        return apiClient.pixabayClient.searchPhotos(query, page + 1, perPage).hits
    }
}