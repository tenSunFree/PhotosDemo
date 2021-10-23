package com.example.photosdemo.room.model

import com.google.gson.annotations.SerializedName

data class RoomPhotosResponse(
    @SerializedName("total")
    val total: Int,
    @SerializedName("hits")
    val hits: List<RoomPhotosDetailResponse>
)