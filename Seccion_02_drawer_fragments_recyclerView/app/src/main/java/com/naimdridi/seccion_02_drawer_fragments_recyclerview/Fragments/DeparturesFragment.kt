package com.naimdridi.seccion_02_drawer_fragments_recyclerview.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naimdridi.my_library_second.Interfaces.Others.toast
import com.naimdridi.seccion_02_drawer_fragments_recyclerview.Adapters.FlightAdapter
import com.naimdridi.seccion_02_drawer_fragments_recyclerview.Listeners.RecyclerFlightListener
import com.naimdridi.seccion_02_drawer_fragments_recyclerview.Models.Flight
import com.naimdridi.seccion_02_drawer_fragments_recyclerview.R
import kotlinx.android.synthetic.main.fragment_departures.view.*


class DeparturesFragment : Fragment() {

    private val list: ArrayList<Flight> by lazy { getFlights() }

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: FlightAdapter
    private val layoutManager by lazy { LinearLayoutManager(context) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        activity?.setTitle(R.string.departures_fragments_title)

        val rootView = inflater.inflate(R.layout.fragment_departures, container, false)

        recycler = rootView.recyclerView as RecyclerView
        setRecyclerView()

        return rootView
    }

    private fun setRecyclerView(){

        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = layoutManager
        adapter = (FlightAdapter(list, object: RecyclerFlightListener{

            override fun onClicks(flight: Flight, position: Int) {
                activity?.toast("Let's go to ${flight.city}!")
            }

            override fun onDelete(flight: Flight, position: Int) {
                list.remove(flight)
                adapter.notifyItemRemoved(position)
                activity?.toast("${flight.city} has been removed!")
            }

        }))
        recycler.adapter = adapter
    }


    private fun getFlights(): ArrayList<Flight> {
        return object: ArrayList<Flight>(){
            init {
                add(Flight(1,"Malaga", R.drawable.malaga))
                add(Flight(2, "Dublin", R.drawable.dublin))
                add(Flight(3, "New York", R.drawable.newyork))
                add(Flight(4, "Roma", R.drawable.rome))
                add(Flight(5, "Toronto", R.drawable.toronto))
                add(Flight(6, "Varsovia", R.drawable.warsaw))
                add(Flight(7, "Sydney", R.drawable.sydney))
                add(Flight(8, "Seoul", R.drawable.seul))
                add(Flight(9, "Shanghai", R.drawable.shanghai))
                add(Flight(10, "Tokyo", R.drawable.tokyo))
                add(Flight(11, "Osaka", R.drawable.osaka))

            }
        }
    }

}
