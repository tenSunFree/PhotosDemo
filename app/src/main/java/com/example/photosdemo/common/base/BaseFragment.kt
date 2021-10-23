package com.example.photosdemo.common.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.photosdemo.common.navigation.Navigation
import com.example.photosdemo.common.navigation.NavigationProvider

abstract class BaseFragment : Fragment() {

    var navigation: Navigation? = null
        private set

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = (parentFragment as? NavigationProvider)?.provideNavigation()
    }

    override fun onDetach() {
        navigation = null
        super.onDetach()
    }

    open fun onScrollToTop() {}
}