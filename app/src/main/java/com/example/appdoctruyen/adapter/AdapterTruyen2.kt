package com.example.appdoctruyen.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.appdoctruyen.MainActivity
import com.example.appdoctruyen.R
import com.example.appdoctruyen.model.Truyen
import com.squareup.picasso.Picasso

class AdapterTruyen2(var activity: Activity, var ds:List<Truyen>):ArrayAdapter<Truyen>(activity, R.layout.truyennew) {
    override fun getCount(): Int {
        return ds.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = activity.layoutInflater.inflate(R.layout.truyennew,parent, false)

        var tenTruyen = view.findViewById<TextView>(R.id.txtTenTruyen)
        var imageTruyen = view.findViewById<ImageView>(R.id.imgTruyen)

        tenTruyen.setText(ds[position].getTenTruyen())

        Picasso.get().load(ds[position].getAnh()).placeholder(R.drawable.load).error(R.drawable.load).into(imageTruyen)

        return view
    }
}