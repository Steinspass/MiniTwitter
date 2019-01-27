package com.naimdridi.seccion_02_drawer_fragments_recyclerview.Activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.naimdridi.my_library_second.Interfaces.Others.toast
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
        setUserHeaderInformation()

        if (savedInstanceState == null ){
            fragmentTransaction(HomeFragment())
            checkedItemNavDrawer()
        }

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

    private fun loadFragmentById(id: Int){
        when(id){
            R.id.nav_home -> fragmentTransaction(HomeFragment())

            R.id.nav_arrivals -> fragmentTransaction(ArrivalsFragment())

            R.id.nav_departures -> fragmentTransaction(DeparturesFragment())
        }

    }

    private fun showMessageNavItemSelectedById(id: Int){
        when(id){

            R.id.nav_profile -> toast("Click Profile")

            R.id.nav_settings -> toast("Click Settings")
        }

    }

    private fun setUserHeaderInformation(){
        val name = navView.getHeaderView(0).findViewById<TextView>(R.id.textViewName)
        val email = navView.getHeaderView(0).findViewById<TextView>(R.id.textViewEmail)

        name?.let { name.text = getString(R.string.user_name) }
        email?.let { email.text = getString(R.string.user_email) }
    }

    private fun checkedItemNavDrawer(){ navView.menu.getItem(0).isChecked =true }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
            showMessageNavItemSelectedById(item.itemId)
            loadFragmentById(item.itemId)
            drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }

    }





}
