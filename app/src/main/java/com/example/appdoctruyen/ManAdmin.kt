package com.example.appdoctruyen

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import com.example.appdoctruyen.adapter.AdapterTruyen2
import com.example.appdoctruyen.database.DatabaseDocTruyen
import com.example.appdoctruyen.model.Truyen

class ManAdmin : AppCompatActivity() {
    lateinit var db:DatabaseDocTruyen
    lateinit var rs:Cursor
    lateinit var adapter:AdapterTruyen2
    lateinit var lvTruyen:ListView
    lateinit var dsTruyen:MutableList<Truyen>
    lateinit var diolog:AlertDialog
    lateinit var tenTaiKhoan:String
    lateinit var email:String
    var phanQuyen:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_man_admin)

        val idTaiKhoan = intent.getIntExtra("id",0)

        tenTaiKhoan = intent.getStringExtra("tentk")!!
        email = intent.getStringExtra("email")!!
        phanQuyen = intent.getIntExtra("phanQuyen", 0)

        lvTruyen = findViewById<ListView>(R.id.lvThemTruyen)
        dsTruyen = mutableListOf<Truyen>()
        db = DatabaseDocTruyen(this)
        rs = db.getData2()
        while(rs.moveToNext()){
            val id = rs.getInt(0)
            val tenTruyen = rs.getString(1)
            val noiDung = rs.getString(2)
            val anh = rs.getString(3)
            val id_tk = rs.getInt(4)

            val truyen = Truyen(id,tenTruyen,noiDung,anh,id_tk)
            dsTruyen.add(truyen)

            adapter = AdapterTruyen2(this, dsTruyen)
            lvTruyen.adapter = adapter

            lvTruyen.setOnItemClickListener { adapterView, view, i, l ->
                showDiolog(i)
            }
        }

        val btn = findViewById<Button>(R.id.btnThemTruyen)
        btn.setOnClickListener {
            val intent = Intent(this, ManDangBai::class.java)
            intent.putExtra("id",idTaiKhoan)
            intent.putExtra("tentk", tenTaiKhoan)
            intent.putExtra("email", email)
            intent.putExtra("phanQuyen", phanQuyen)
            finish() //đóng activity hiện tại
            startActivity(intent)
        }

        actionToolbar()
    }

    fun showDiolog(position:Int){
        val build = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.customdiolog, null)
        build.setView(view)
        val btnYes = view.findViewById<Button>(R.id.btnYes)
        val btnNo = view.findViewById<Button>(R.id.btnNo)
        btnYes.setOnClickListener {
            val idTruyen = dsTruyen[position].getId_truyen()
            db.deleteTruyen(idTruyen)

            //cập nhập lại activity
            val intent = Intent(this, ManAdmin::class.java)
            intent.putExtra("tentk", tenTaiKhoan)
            intent.putExtra("email", email)
            intent.putExtra("phanQuyen", phanQuyen)
            finish()
            startActivity(intent)
            Toast.makeText(this, "Xóa truyện thành công", Toast.LENGTH_SHORT).show()
        }
        btnNo.setOnClickListener {
            diolog.dismiss()
        }
        diolog = build.create()
        diolog.show()
    }

    fun actionToolbar(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationIcon(R.drawable.home)
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("tentk", tenTaiKhoan)
            intent.putExtra("email", email)
            intent.putExtra("phanQuyen", phanQuyen)
            finish()
            startActivity(intent)
        }
    }
}