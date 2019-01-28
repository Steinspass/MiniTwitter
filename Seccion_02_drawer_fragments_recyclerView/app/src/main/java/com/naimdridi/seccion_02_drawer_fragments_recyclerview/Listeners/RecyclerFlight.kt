package com.naimdridi.seccion_02_drawer_fragments_recyclerview.Listeners

import com.naimdridi.seccion_02_drawer_fragments_recyclerview.Models.Flight

interface RecyclerFlightListener{

    fun onClicks(flight: Flight, position: Int)
    fun onDelete(flight: Flight, position: Int)
}