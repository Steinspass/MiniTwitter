package com.naimdridi.seccion_01_dev_android_in_kotlin.Adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.naimdridi.seccion_01_dev_android_in_kotlin.Others.inflateM
import com.naimdridi.seccion_01_dev_android_in_kotlin.models.Person
import kotlinx.android.synthetic.main.list_view_person.view.*

class PersonAdapter(val context: Context,val layout: Int, val list: List<Person>) : BaseAdapter(){



    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        val vh: PersonViewHolder

        if (convertView == null){
            view = parent.inflateM(layout)
            vh = PersonViewHolder(view)
            view.tag = vh
        }else{
            view = convertView
            vh = view.tag as PersonViewHolder

        }

        val fullName = "${list[position].firstName},${list[position].lastName}"
        vh.fullName.text = fullName
        vh.Age.text = "${list[position].age}"

        return view

    }


}


private class PersonViewHolder(view: View){

    val fullName: TextView = view.textViewName
    val Age: TextView = view.textViewAge
}