package com.soundsstories

import android.content.res.Resources
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.soundsstories.ui.main.SectionsPagerAdapter
import com.soundsstories.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pages = getData()
        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, pages)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }

    private fun getData(): SoundTemplate? {
        val homeString = resources.getRawTextFile(R.raw.sounds)
        return Gson().fromJson(homeString, SoundTemplate::class.java)
    }

    private fun Resources.getRawTextFile(@RawRes id: Int) = openRawResource(id).bufferedReader().use { it.readText() }

}