package com.naimdridi.finalapp.Activities

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.naimdridi.finalapp.Activities.Adapters.PagerAdapter
import com.naimdridi.finalapp.Activities.Fragments.ChatFragment
import com.naimdridi.finalapp.Activities.Fragments.InfoFragment
import com.naimdridi.finalapp.Activities.Fragments.RateFragment
import com.naimdridi.finalapp.R
import com.naimdridi.my_library_second.Interfaces.ToolbarActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ToolbarActivity() {


    private var prevBottomSelected: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarToLoad(toolbarView as Toolbar)

        setUpViewPager(getPagerAdapter())
        setUpBottomNavigationBar()

    }

    private fun getPagerAdapter(): PagerAdapter{
        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addFragment(InfoFragment())
        adapter.addFragment(ChatFragment())
        adapter.addFragment(RateFragment())
        return adapter
    }

    private fun setUpViewPager(adapter: PagerAdapter){
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(p0: Int) {}

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

            override fun onPageSelected(position: Int) {
                if (prevBottomSelected == null){
                    btn_nav_View.menu.getItem(0).isChecked = false
                }else{
                    prevBottomSelected!!.isChecked = false
                }
                btn_nav_View.menu.getItem(position).isChecked = true
                prevBottomSelected = btn_nav_View.menu.getItem(position)
            }

        })

    }

    private fun setUpBottomNavigationBar(){
        btn_nav_View.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.bottom_nav_info -> {
                    viewPager.currentItem = 0; true
                }

                R.id.bottom_nav_chat -> {
                    viewPager.currentItem = 1; true
                }

                R.id.bottom_nav_rate -> {
                    viewPager.currentItem = 2; true
                }
                else -> false
            }
        }
    }
}
