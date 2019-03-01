package com.naimdridi.ajetpackresponsivedesign

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity(), NotesIteractionListener {


    private var prevBottomSelected: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setUpViewPager(getPagerAdapter())
        setUpNavigation()


    }

    private fun getPagerAdapter(): PagerAdapter{
        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addFragment(NotesFragment())

        return adapter
    }

    private fun setUpViewPager(adapter: PagerAdapter){
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = adapter.count
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(position: Int) {

                if (prevBottomSelected == null){
                    navigation.menu.getItem(0).isChecked = false
                }else{
                    prevBottomSelected!!.isChecked = false
                }
                navigation.menu.getItem(position).isChecked = true
                prevBottomSelected = navigation.menu.getItem(position)

            }

        })
    }



    private fun setUpNavigation(){
        navigation.setOnNavigationItemSelectedListener {item ->

            when (item.itemId) {
                R.id.navigation_home -> {
                    viewPager.currentItem = 0; true
                }
                else -> false
            }

        }

    }


    override fun editNotaClick(note: Note) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteNoteClick(note: Note) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun favoriteNoteClick(note: Note) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
