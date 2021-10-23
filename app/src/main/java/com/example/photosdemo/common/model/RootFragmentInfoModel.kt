package com.example.photosdemo.common.model

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

data class RootFragmentInfoModel(
    val fragment: Fragment,
    @IdRes val itemId: Int
)