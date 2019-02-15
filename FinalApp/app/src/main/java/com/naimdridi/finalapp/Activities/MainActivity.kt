package com.naimdridi.finalapp.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.naimdridi.finalapp.Activities.Adapters.PagerAdapter
import com.naimdridi.finalapp.Activities.Fragments.ChatFragment
import com.naimdridi.finalapp.Activities.Fragments.InfoFragment
import com.naimdridi.finalapp.Activities.Fragments.RateFragment
import com.naimdridi.finalapp.Activities.loginActivities.LoginActivity
import com.naimdridi.finalapp.R
import com.naimdridi.my_library_second.Interfaces.Others.goToActivity
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
        viewPager.offscreenPageLimit = adapter.count
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.general_options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_log_out -> {
                FirebaseAuth.getInstance().signOut()
                goToActivity<LoginActivity> {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
