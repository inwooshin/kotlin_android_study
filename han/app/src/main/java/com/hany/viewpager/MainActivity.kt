package com.hany.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.hany.viewpager.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val fragmentList = listOf(FragmentA(),FragmentB(), FragmentC())
        val adapter = FragmentAdapter(this)
        adapter.fragmentList = fragmentList
        binding.viewPager.adapter = adapter
        val tabTitles = listOf<String>("A","B","C")
        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position ->tab.text =tabTitles[position]}.attach()
    }
}