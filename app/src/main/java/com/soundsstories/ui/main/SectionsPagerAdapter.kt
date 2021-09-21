package com.soundsstories.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.soundsstories.SoundTemplate

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentManager, private val pages: SoundTemplate?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return PlaceholderFragment.newInstance(pages!!.pages[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages?.pages?.get(position)?.title
    }

    override fun getCount(): Int {
        return pages?.pages?.size!!
    }
}