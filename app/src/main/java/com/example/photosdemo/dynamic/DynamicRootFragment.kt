package com.example.photosdemo.dynamic

import androidx.fragment.app.Fragment
import com.example.photosdemo.common.base.RootFragment

class DynamicRootFragment : RootFragment() {

    override fun provideRootFragment(): Fragment {
        return DynamicFragment()
    }
}