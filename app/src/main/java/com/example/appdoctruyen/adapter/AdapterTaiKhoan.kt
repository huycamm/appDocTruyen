package com.example.appdoctruyen.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.appdoctruyen.R
import com.example.appdoctruyen.model.TaiKhoan

class AdapterTaiKhoan(var activity: Activity, var ds: List<TaiKhoan>):ArrayAdapter<TaiKhoan>(activity, R.layout.navigation) {
    override fun getCount(): Int {
        return ds.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = activity.layoutInflater.inflate(R.layout.navigation, parent, false)

        val tenTaiKhoan = view.findViewById<TextView>(R.id.txtName)
        val email = view.findViewById<TextView>(R.id.txtEmail)

        tenTaiKhoan.setText(ds[position].getTenTaiKhoan())
        email.setText(ds[position].getEmail())

        return view
    }
}