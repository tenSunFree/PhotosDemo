package com.example.photosdemo.mine

import androidx.fragment.app.Fragment
import com.example.photosdemo.common.base.RootFragment

class MineRootFragment : RootFragment() {

    override fun provideRootFragment(): Fragment {
        return MineFragment()
    }
}