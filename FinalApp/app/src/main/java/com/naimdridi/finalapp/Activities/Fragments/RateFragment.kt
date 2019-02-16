package com.naimdridi.finalapp.Activities.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.naimdridi.finalapp.R




class RateFragment : Fragment() {

    private lateinit var _view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
         _view = inflater.inflate(R.layout.fragment_rate, container, false)


        return _view
    }


}
