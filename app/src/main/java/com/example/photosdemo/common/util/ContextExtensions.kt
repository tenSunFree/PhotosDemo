package com.example.photosdemo.common.util

import android.app.Activity
import android.content.*
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Context.showSoftInput(view: View): Boolean {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    return inputMethodManager.showSoftInput(view, 0)
}

fun Context.hideSoftInput(view: View) {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.hideSoftInput() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}