package com.example.photosdemo.room.model

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class RoomPhotosDetailResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("largeImageURL")
    val largeImageUrl: String,
    @SerializedName("user")
    val user: String,
) : RoomPhotos() {
    override fun largeImageUrl(): String {
        return largeImageUrl
    }

    override fun user(): String {
        return user
    }
}