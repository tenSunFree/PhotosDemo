package com.example.photosdemo.discover

import androidx.fragment.app.Fragment
import com.example.photosdemo.common.base.RootFragment

class DiscoverRootFragment : RootFragment() {

    override fun provideRootFragment(): Fragment {
        return DiscoverFragment()
    }
}