package com.example.photosdemo.room.model

import android.os.Parcelable

abstract class RoomPhotos : Parcelable {

    open fun id(): String = ""
    open fun largeImageUrl(): String = ""
    open fun user(): String = ""

    override fun equals(other: Any?): Boolean {
        return if (other !is RoomPhotos) {
            false
        } else {
            this.id() == other.id()
        }
    }

    override fun hashCode(): Int {
        return id().hashCode()
    }
}