package com.example.appdoctruyen.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.appdoctruyen.R
import com.example.appdoctruyen.model.ChuyenMuc

class AdapterChuyenMuc(var activity: Activity, var ds:List<ChuyenMuc>):ArrayAdapter<ChuyenMuc>(activity, R.layout.chuyenmuc) {
    override fun getCount(): Int {
        return ds.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = activity.layoutInflater.inflate(R.layout.chuyenmuc, parent, false)

        val ten = view.findViewById<TextView>(R.id.txtTenChuyenMuc)
        val imageChuyenMuc = view.findViewById<ImageView>(R.id.imgChuyenMuc)

        ten.setText(ds[position].getChuyenMuc())
        imageChuyenMuc.setImageResource(ds[position].getHinhAnh())

        return view
    }
}