package com.naimdridi.finalapp.Activities.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naimdridi.finalapp.Activities.Adapters.RateAdapter
import com.naimdridi.finalapp.Activities.Models.Rate
import com.naimdridi.finalapp.Activities.dialog.RateDialog

import com.naimdridi.finalapp.R
import kotlinx.android.synthetic.main.fragment_rate.view.*
import java.util.ArrayList


class RateFragment : Fragment() {

    private lateinit var _view: View

    private lateinit var adapter: RateAdapter
    private val ratesList: ArrayList<Rate> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
         _view = inflater.inflate(R.layout.fragment_rate, container, false)

        setUpRecyclerView()
        setUpFab()

        return _view
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        adapter = RateAdapter(ratesList)

        _view.recyclerViewRate.setHasFixedSize(true)
        _view.recyclerViewRate.layoutManager = layoutManager
        _view.recyclerViewRate.itemAnimator = DefaultItemAnimator()
        _view.recyclerViewRate.adapter = adapter
    }

    private fun setUpFab() {
        _view.fabRating.setOnClickListener { RateDialog().show(fragmentManager, "") }
    }


}
