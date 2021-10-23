package com.example.photosdemo.room.model

interface RoomRepository {
    suspend fun searchPhotos(query: String, page: Int, perPage: Int): List<RoomPhotos>
}