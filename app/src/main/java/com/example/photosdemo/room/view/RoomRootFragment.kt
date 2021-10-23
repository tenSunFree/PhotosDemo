package com.example.photosdemo.room.view

import androidx.fragment.app.Fragment
import com.example.photosdemo.common.base.RootFragment

class RoomRootFragment : RootFragment() {

    override fun provideRootFragment(): Fragment {
        return RoomFragment()
    }
}