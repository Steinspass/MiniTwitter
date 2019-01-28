package com.naimdridi.seccion_02_drawer_fragments_recyclerview.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naimdridi.seccion_02_drawer_fragments_recyclerview.Models.Flight
import com.naimdridi.seccion_02_drawer_fragments_recyclerview.R



class DeparturesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_departures, container, false)
    }


    private fun getFlights(): ArrayList<Flight> {
        return object: ArrayList<Flight>(){
            init {
                add(Flight(0,"Malaga", R.drawable.malaga))
                add(Flight(1, "Dublin", R.drawable.dublin))
                add(Flight(2, "New York", R.drawable.newyork))
                add(Flight(3, "Roma", R.drawable.rome))
                add(Flight(4, "Toronto", R.drawable.toronto))
                add(Flight(5, "Warsaw", R.drawable.warsaw))
                add(Flight(6, "Sydney", R.drawable.sydney))
                add(Flight(7, "Seoul", R.drawable.seul))
                add(Flight(8, "Shanghai", R.drawable.shanghai))
                add(Flight(9, "Tokyo", R.drawable.tokyo))
                add(Flight(10, "Osaka", R.drawable.osaka))

            }
        }
    }

}
