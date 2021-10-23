package com.example.photosdemo.common.remote

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiClient @Inject constructor(
        val pixabayClient: PixabayClient
)