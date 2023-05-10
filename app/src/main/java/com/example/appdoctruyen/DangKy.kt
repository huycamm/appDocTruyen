package com.example.appdoctruyen

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.appdoctruyen.database.DatabaseDocTruyen
import com.example.appdoctruyen.databinding.ActivityDangKyBinding
import com.example.appdoctruyen.model.TaiKhoan

lateinit var binding3: ActivityDangKyBinding
class DangKy : AppCompatActivity() {
    lateinit var databaseDocTruyen: DatabaseDocTruyen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding3 = ActivityDangKyBinding.inflate(layoutInflater)
        setContentView(binding3.root)

        databaseDocTruyen = DatabaseDocTruyen(this)

        binding3.btnDkDangNhap.setOnClickListener {
            val intent = Intent(this, DangNhap::class.java)
            startActivity(intent)
        }

        binding3.btnDkDangKy.setOnClickListener {
            //khởi tạo tài khoản
            var tenTaiKhoan = binding3.edtDkTaiKhoan.text.toString()
            var matKhau = binding3.edtDkMatKhau.text.toString()
            var email = binding3.edtDkEmail.text.toString()
            var phanQuyen = 1

            var taiKhoan = TaiKhoan(tenTaiKhoan,matKhau,email,phanQuyen)

            if(tenTaiKhoan.equals("") || matKhau.equals("") || email.equals("")){
                Toast.makeText(this, "Nhập đầy đủ thông tin đăng ký", Toast.LENGTH_SHORT).show()
            }else{
            databaseDocTruyen.insertData(taiKhoan)

            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
            binding3.edtDkTaiKhoan.setText("")
            binding3.edtDkMatKhau.setText("")
            binding3.edtDkEmail.setText("")
            binding3.edtDkTaiKhoan.requestFocus()
            }
        }
    }
}