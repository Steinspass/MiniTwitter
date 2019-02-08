package com.naimdridi.finalapp.Activities.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.ArrayList

class PagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    private val fragmentList = ArrayList<Fragment>()


    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size

    fun addFragment(fragment: Fragment) = fragmentList.add(fragment)


}