package com.example.photosdemo.common.remote

import com.example.photosdemo.room.model.RoomRepository
import com.example.photosdemo.room.model.RoomRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providePhotosRepository(photosRepository: RoomRepositoryImpl): RoomRepository {
        return photosRepository
    }
}