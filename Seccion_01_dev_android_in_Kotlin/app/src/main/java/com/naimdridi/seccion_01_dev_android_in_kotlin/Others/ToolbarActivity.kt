package com.naimdridi.seccion_01_dev_android_in_kotlin.Others

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.naimdridi.seccion_01_dev_android_in_kotlin.models.iToolbar

open class ToolbarActivity : AppCompatActivity(), iToolbar {

    protected var _toolbar: Toolbar? = null

    override fun toolbarToLoad(toolbar: Toolbar?) {
        _toolbar = toolbar
        _toolbar?.let { setSupportActionBar(_toolbar) }
    }

    override fun enableHomeDisplay(values: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(values)
    }


}
