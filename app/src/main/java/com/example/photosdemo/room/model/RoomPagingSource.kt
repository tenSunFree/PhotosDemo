package com.example.photosdemo.room.model

import androidx.paging.PagingSource
import androidx.paging.PagingState

class RoomPagingSource(
    private val roomRepository: RoomRepository,
    private val searchQuery: String
) : PagingSource<Int, RoomPhotos>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RoomPhotos> {
        val initialPosition = 0
        val position = params.key ?: initialPosition
        return try {
            val photos = roomRepository
                .searchPhotos(searchQuery, position, params.loadSize)
            LoadResult.Page(
                data = photos,
                prevKey = if (position == initialPosition) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RoomPhotos>): Int? {
        return state.anchorPosition
    }
}