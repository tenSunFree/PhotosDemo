package com.example.photosdemo.message

import androidx.fragment.app.Fragment
import com.example.photosdemo.common.base.RootFragment

class MessageRootFragment : RootFragment() {

    override fun provideRootFragment(): Fragment {
        return MessageFragment()
    }
}