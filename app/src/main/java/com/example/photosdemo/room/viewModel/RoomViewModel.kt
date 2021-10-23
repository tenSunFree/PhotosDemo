package com.example.photosdemo.room.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.photosdemo.room.model.RoomRepository
import com.example.photosdemo.room.model.RoomPagingSource
import com.example.photosdemo.room.model.RoomPhotos
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    fun searchPhotos(query: String): LiveData<PagingData<RoomPhotos>>? {
        val queryTrimmed = query.trim()
        if (queryTrimmed.isEmpty()) return null
        return Pager(
            config = PagingConfig(
                initialLoadSize = 20,
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                RoomPagingSource(roomRepository, queryTrimmed)
            }
        ).liveData
    }
}