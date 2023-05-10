package com.example.appdoctruyen

import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.appdoctruyen.database.DatabaseDocTruyen
import com.example.appdoctruyen.databinding.ActivityDangNhapBinding

lateinit var binding2: ActivityDangNhapBinding
class DangNhap : AppCompatActivity() {
    lateinit var databaseDocTruyen:DatabaseDocTruyen
    lateinit var rs:Cursor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivityDangNhapBinding.inflate(layoutInflater)
        setContentView(binding2.root)

        databaseDocTruyen = DatabaseDocTruyen(this)

        binding2.btnDangKy.setOnClickListener {
            val intent = Intent(this, DangKy::class.java)
            startActivity(intent)
        }

        binding2.btnDangNhap.setOnClickListener {
            val taiKhoan = binding2.edtTaiKhoan.text.toString()
            val matKhau = binding2.edtMatKhau.text.toString()
            if(taiKhoan.equals("") || matKhau.equals("")){
                Toast.makeText(this, "Nhập đầy đủ thông tin đăng nhập", Toast.LENGTH_SHORT).show()
            }else{
                rs = databaseDocTruyen.getData()
                var check = false
                while(rs.moveToNext()){
                    val rsTaiKhoan = rs.getString(1)
                    val rsMatKhau = rs.getString(2)
                    if(rsTaiKhoan.equals(taiKhoan) && rsMatKhau.equals(matKhau)){
                        val tentk = rs.getString(1)
                        val idd = rs.getInt(0)
                        val email = rs.getString(3)
                        val phanQuyen = rs.getInt(4)

                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("tentk", tentk)
                        intent.putExtra("id", idd)
                        intent.putExtra("email", email)
                        intent.putExtra("phanQuyen", phanQuyen)

                        startActivity(intent)
                        check = true
                    }
                }
                rs.moveToFirst()
                rs.close()
                if(!check){
                    val ad = AlertDialog.Builder(this)
                    ad.setTitle("Đăng nhập")
                    ad.setMessage("Sai tên đăng nhập hoặc mật khẩu")
                    ad.setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->
                        binding2.edtTaiKhoan.setText("")
                        binding2.edtMatKhau.setText("")
                        binding2.edtTaiKhoan.requestFocus()
                    })
                    ad.show()
                }
            }
        }
    }
}