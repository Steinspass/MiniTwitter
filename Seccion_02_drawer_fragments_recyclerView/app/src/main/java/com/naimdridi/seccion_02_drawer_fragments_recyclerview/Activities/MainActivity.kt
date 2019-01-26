package com.naimdridi.seccion_02_drawer_fragments_recyclerview.Activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.naimdridi.my_library_second.Interfaces.ToolbarActivity
import com.naimdridi.seccion_02_drawer_fragments_recyclerview.Fragments.ArrivalsFragment
import com.naimdridi.seccion_02_drawer_fragments_recyclerview.Fragments.DeparturesFragment
import com.naimdridi.seccion_02_drawer_fragments_recyclerview.Fragments.HomeFragment
import com.naimdridi.seccion_02_drawer_fragments_recyclerview.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ToolbarActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarToLoad(toolbar as Toolbar)
        setNavDrawer()
        checkedItemNavDrawer()
        fragmentTransaction(HomeFragment())

    }


    private fun setNavDrawer(){
        val toggle = ActionBarDrawerToggle(this, drawerLayout, _toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    private fun fragmentTransaction(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> fragmentTransaction(HomeFragment())

            R.id.nav_arrivals -> fragmentTransaction(ArrivalsFragment())

            R.id.nav_departures -> fragmentTransaction(DeparturesFragment())
        }
        return true
    }

    private fun checkedItemNavDrawer(){ navView.menu.getItem(0).isChecked =true }

}
