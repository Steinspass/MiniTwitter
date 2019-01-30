package com.naimdridi.my_library_second.Interfaces

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.naimdridi.my_library_second.Interfaces.Interfaces.Itoolbar

open class ToolbarActivity : AppCompatActivity(), Itoolbar{

    protected var _toolbar: Toolbar? = null

    override fun toolbarToLoad(toolbar: Toolbar) {
        _toolbar = toolbar
        _toolbar?.let { setSupportActionBar(_toolbar) }
    }

    override fun enabledHomeDisplay(value: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(value)
    }

}