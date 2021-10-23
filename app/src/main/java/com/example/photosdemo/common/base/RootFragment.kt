package com.example.photosdemo.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.photosdemo.common.navigation.Navigation
import com.example.photosdemo.common.navigation.NavigationDispatcher
import com.example.photosdemo.common.navigation.NavigationProvider
import com.example.photosdemo.R

abstract class RootFragment : Fragment(), NavigationProvider {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_root, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            provideNavigation().addFragment(provideRootFragment(), withAnimation = false)
        }
    }

    override fun provideNavigation(): Navigation {
        return NavigationDispatcher(this, R.id.frame_layout)
    }

    abstract fun provideRootFragment(): Fragment
}