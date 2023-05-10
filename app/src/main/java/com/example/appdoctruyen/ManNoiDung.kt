package com.example.appdoctruyen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import android.widget.TextView

class ManNoiDung : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_man_noi_dung)

        var tenTruyen = findViewById<TextView>(R.id.txtNDTenTruyen)
        var noiDung = findViewById<TextView>(R.id.txtNDNoiDung)

        var tt = intent.getStringExtra("tenTruyen")
        var nd = intent.getStringExtra("noiDung")

        tenTruyen.setText(tt)
        noiDung.setText(nd)

    }
}