package com.naimdridi.seccion_01_dev_android_in_kotlin.models

import android.support.v7.widget.Toolbar

interface iToolbar {
    fun toolbarToLoad(toolbar: Toolbar?)
    fun enableHomeDisplay(values: Boolean)
}