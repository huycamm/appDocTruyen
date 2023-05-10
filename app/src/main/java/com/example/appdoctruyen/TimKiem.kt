package com.example.appdoctruyen

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SearchView
import com.example.appdoctruyen.adapter.AdapterTruyen2
import com.example.appdoctruyen.database.DatabaseDocTruyen
import com.example.appdoctruyen.model.Truyen

class TimKiem : AppCompatActivity() {
    lateinit var db:DatabaseDocTruyen
    lateinit var rs:Cursor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tim_kiem)

        val lvTruyen = findViewById<ListView>(R.id.lvTruyen)
        val textFind = findViewById<SearchView>(R.id.search_view)

        var dsTruyen = mutableListOf<Truyen>()

        db = DatabaseDocTruyen(this)
        rs = db.getData2()
        while(rs.moveToNext()){
            val id = rs.getInt(0)
            val ten = rs.getString(1)
            val noiDung = rs.getString(2)
            val anh = rs.getString(3)
            val id_tk = rs.getInt(4)

            val truyen = Truyen(id,ten,noiDung,anh,id_tk)
            dsTruyen.add(truyen)
        }
        val adapter = AdapterTruyen2(this,dsTruyen)
        lvTruyen.adapter = adapter

        textFind.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                dsTruyen.clear()
                val data = db.readableDatabase
                rs = data.rawQuery("select * from truyen where tieude like '%$p0%'",null)
                while (rs.moveToNext()){
                    val id = rs.getInt(0)
                    val ten = rs.getString(1)
                    val noiDung = rs.getString(2)
                    val anh = rs.getString(3)
                    val id_tk = rs.getInt(4)
                    val truyen = Truyen(id,ten,noiDung,anh,id_tk)
                    dsTruyen.add(truyen)

                    adapter.notifyDataSetChanged()
                }
                return true
            }
        })

        lvTruyen.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, ManNoiDung::class.java)
            val tenTruyen = dsTruyen[i].getTenTruyen()
            val noiDung = dsTruyen[i].getNoiDung()

            intent.putExtra("tenTruyen", tenTruyen)
            intent.putExtra("noiDung", noiDung)
            startActivity(intent)
        }
    }
}