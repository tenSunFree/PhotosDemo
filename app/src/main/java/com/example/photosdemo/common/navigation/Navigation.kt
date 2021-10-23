package com.example.photosdemo.common.navigation

import androidx.fragment.app.Fragment

interface Navigation {
    fun addFragment(fragment: Fragment, withAnimation: Boolean = true)
    fun backPressed(): Boolean
    fun backToRoot(): Fragment?
}