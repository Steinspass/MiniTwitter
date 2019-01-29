package com.naimdridi.seccion_02_drawer_fragments_recyclerview.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naimdridi.seccion_02_drawer_fragments_recyclerview.R


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.setTitle(R.string.home_fragment_title)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}
