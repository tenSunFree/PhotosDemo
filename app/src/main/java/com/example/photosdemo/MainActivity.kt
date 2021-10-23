package com.example.photosdemo

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.photosdemo.common.base.BaseFragment
import com.example.photosdemo.common.base.RootFragment
import com.example.photosdemo.common.util.customTag
import com.example.photosdemo.common.model.RootFragmentInfoModel
import com.example.photosdemo.databinding.ActivityMainBinding
import com.example.photosdemo.discover.DiscoverRootFragment
import com.example.photosdemo.dynamic.DynamicRootFragment
import com.example.photosdemo.message.MessageRootFragment
import com.example.photosdemo.mine.MineRootFragment
import com.example.photosdemo.room.view.RoomRootFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val fragments = listOf(
        RootFragmentInfoModel(
            RoomRootFragment(),
            R.id.home
        ),
        RootFragmentInfoModel(
            DynamicRootFragment(),
            R.id.photos
        ),
        RootFragmentInfoModel(
            DiscoverRootFragment(),
            R.id.search
        ),
        RootFragmentInfoModel(
            MessageRootFragment(),
            R.id.favorites
        ),
        RootFragmentInfoModel(
            MineRootFragment(),
            R.id.more
        )
    )

    private lateinit var binding: ActivityMainBinding
    private val initialFragmentInfoModelIndex = 0
    private var initialFragmentInfoModel = fragments[initialFragmentInfoModelIndex]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) setupBottomNavigation()
        setNavigationListener()
    }

    override fun onBackPressed() {
        if ((getActiveFragment() as? RootFragment)?.provideNavigation()?.backPressed() == false) {
            if (isInitialFragmentSelected()) {
                selectInitialFragment()
            } else {
                super.onBackPressed()
            }
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.selectedItemId = initialFragmentInfoModel.itemId
        fragments.forEach { fragmentInfo ->
            val transaction = supportFragmentManager.beginTransaction()
                .add(
                    R.id.frame_layout,
                    fragmentInfo.fragment,
                    fragmentInfo.fragment.customTag()
                )
            if (fragmentInfo != initialFragmentInfoModel) transaction.hide(fragmentInfo.fragment)
            transaction.commit()
        }
    }

    private fun setNavigationListener() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            changeFragment(menuItem.itemId)
            true
        }
        binding.bottomNavigationView.setOnNavigationItemReselectedListener { menuItem ->
            val fragmentTag = getFragmentTagByItemId(menuItem.itemId)
            val fragment = findFragment(fragmentTag) as RootFragment
            if (fragment.isAdded) {
                (fragment.provideNavigation().backToRoot() as? BaseFragment)?.onScrollToTop()
            }
        }
    }

    private fun getFragmentTagByItemId(itemId: Int): String? {
        return fragments.find { it.itemId == itemId }?.fragment?.customTag()
    }

    private fun changeFragment(@IdRes itemId: Int) {
        val hideFragment = findFragment(getActiveFragment()?.customTag())
        val showFragment = findFragment(getFragmentTagByItemId(itemId))
        val transaction = supportFragmentManager.beginTransaction()
        if (hideFragment != null) transaction.hide(hideFragment)
        if (showFragment != null) transaction.show(showFragment)
        transaction.commit()
    }

    private fun findFragment(tag: String?): Fragment? {
        tag?.let {
            return supportFragmentManager.findFragmentByTag(it)
        } ?: run {
            return null
        }
    }

    private fun getActiveFragment(): Fragment? {
        supportFragmentManager.fragments.forEach {
            if (it.isVisible) return it
        }
        return null
    }

    private fun isInitialFragmentSelected(): Boolean {
        return binding.bottomNavigationView.selectedItemId != initialFragmentInfoModel.itemId
    }

    private fun selectInitialFragment() {
        binding.bottomNavigationView.selectedItemId = initialFragmentInfoModel.itemId
    }
}