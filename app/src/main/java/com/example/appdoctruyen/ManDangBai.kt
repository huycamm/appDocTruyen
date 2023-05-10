package com.example.appdoctruyen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appdoctruyen.database.DatabaseDocTruyen
import com.example.appdoctruyen.databinding.ActivityManDangBaiBinding
import com.example.appdoctruyen.model.Truyen

lateinit var bindingDB: ActivityManDangBaiBinding
class ManDangBai : AppCompatActivity() {
    lateinit var db:DatabaseDocTruyen
    lateinit var tenTaiKhoan:String
    lateinit var email:String
    var phanQuyen:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDB = ActivityManDangBaiBinding.inflate(layoutInflater)
        setContentView(bindingDB.root)

        tenTaiKhoan = intent.getStringExtra("tentk")!!
        email = intent.getStringExtra("email")!!
        phanQuyen = intent.getIntExtra("phanQuyen", 0)

        db = DatabaseDocTruyen(this)

        bindingDB.btnDangBai.setOnClickListener {
            val tenT = bindingDB.edtDbTenTruyen.getText().toString()
            val noiDung = bindingDB.edtDbNoiDung.text.toString()
            val anh = bindingDB.edtDbAnh.text.toString()

            val truyen = createTruyen()
            if(tenT.equals("") || noiDung.equals("") || anh.equals("")){
                Toast.makeText(this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }else{
                db.addTruyen(truyen)
                val intent = Intent(this, ManAdmin::class.java)
                intent.putExtra("tentk", tenTaiKhoan)
                intent.putExtra("email", email)
                intent.putExtra("phanQuyen", phanQuyen)
                finish()
                startActivity(intent)
            }
        }
    }

    fun createTruyen():Truyen{
        val tenT = bindingDB.edtDbTenTruyen.getText().toString()
        val noiDung = bindingDB.edtDbNoiDung.text.toString()
        val anh = bindingDB.edtDbAnh.text.toString()
        val id = intent.getIntExtra("id",0)

        val truyen = Truyen(tenT, noiDung, anh, id)
        return truyen
    }
}