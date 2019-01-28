package com.naimdridi.seccion_02_drawer_fragments_recyclerview.Adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.naimdridi.my_library_second.Interfaces.Others.inflateM
import com.naimdridi.my_library_second.Interfaces.Others.loadByResource
import com.naimdridi.seccion_02_drawer_fragments_recyclerview.Listeners.RecyclerFlightListener
import com.naimdridi.seccion_02_drawer_fragments_recyclerview.Models.Flight
import com.naimdridi.seccion_02_drawer_fragments_recyclerview.R
import kotlinx.android.synthetic.main.recycler_flight.view.*

class FlightAdapter(private val flights: List<Flight>, private val listener: RecyclerFlightListener)
    : RecyclerView.Adapter<FlightAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflateM(R.layout.recycler_flight))



    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(flights[position], listener)



    override fun getItemCount() = flights.size



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(flight: Flight, listener: RecyclerFlightListener) = with(itemView){

            textViewCityName.text = flight.city
            imageViewBg.loadByResource(flight.imgResource)
            // Click Events
            setOnClickListener { listener.onClicks(flight, adapterPosition) }
            button_delete.setOnClickListener { listener.onDelete(flight, adapterPosition) }
        }
    }
}