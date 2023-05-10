package com.example.appdoctruyen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ManThongTin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_man_thong_tin)

        var thongTin = findViewById<TextView>(R.id.txtThongTin)
        thongTin.setText("Ứng dụng được thực hiện bởi Huyyyyyyyyy")
    }
}