package com.example.photosdemo.common.util

import androidx.fragment.app.Fragment

fun Fragment.customTag(): String {
    return this::class.java.simpleName
}