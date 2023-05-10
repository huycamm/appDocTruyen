package com.example.appdoctruyen

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.appdoctruyen.adapter.AdapterChuyenMuc
import com.example.appdoctruyen.adapter.AdapterTaiKhoan
import com.example.appdoctruyen.adapter.AdapterTruyen2
import com.example.appdoctruyen.database.DatabaseDocTruyen
import com.example.appdoctruyen.databinding.ActivityMainBinding
import com.example.appdoctruyen.model.ChuyenMuc
import com.example.appdoctruyen.model.TaiKhoan
import com.example.appdoctruyen.model.Truyen
import com.squareup.picasso.Picasso

lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    lateinit var db:DatabaseDocTruyen
    lateinit var rs:Cursor

    lateinit var email:String
    lateinit var tenTaiKhoan:String
    var id:Int = 0
    var phanQuyen:Int = 0

    var ds = mutableListOf<Truyen>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        phanQuyen = intent.getIntExtra("phanQuyen", 0)
        id = intent.getIntExtra("id", 0)
        tenTaiKhoan = intent.getStringExtra("tentk")!!
        email = intent.getStringExtra("email")!!

        actionNavigation()
        actionBar()
        actionViewFlipper()
        actionListTruyen()

    }

    private fun actionNavigation() {
        var dsTaiKhoan = mutableListOf<TaiKhoan>()
        val taikhoan = TaiKhoan(tenTaiKhoan, email)
        dsTaiKhoan.add(taikhoan)
        binding.listViewThongTin.adapter = AdapterTaiKhoan(this, dsTaiKhoan)

        var dsChuyenMuc = mutableListOf<ChuyenMuc>()
        var chuyenMuc1 = ChuyenMuc("Đăng bài", R.drawable.icon_poss)
        var chuyenMuc2 = ChuyenMuc("Thông tin", R.drawable.icon_face)
        var chuyenMuc3 = ChuyenMuc("Đăng xuất", R.drawable.icon_exit)
        dsChuyenMuc.add(chuyenMuc1)
        dsChuyenMuc.add(chuyenMuc2)
        dsChuyenMuc.add(chuyenMuc3)
        binding.listViewManHinhChinh.adapter = AdapterChuyenMuc(this, dsChuyenMuc)
        binding.listViewManHinhChinh.setOnItemClickListener { adapterView, view, position, l ->
            if(position==0){
                if(phanQuyen==2){
                    val intent = Intent(this, ManAdmin::class.java)
                    intent.putExtra("id",id)
                    intent.putExtra("tentk", tenTaiKhoan)
                    intent.putExtra("email", email)
                    intent.putExtra("phanQuyen",phanQuyen)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "Bạn không có quyền đăng bài", Toast.LENGTH_SHORT).show()
                }
            }else if(position==1){
                val intent = Intent(this, ManThongTin::class.java)
                startActivity(intent)
            }else if(position==2){
                val intent = Intent(this, DangNhap::class.java)
                finish()
                startActivity(intent)
            }
        }
    }

    private fun actionListTruyen() {
        db = DatabaseDocTruyen(this)
        rs = db.getTruyen()
        while (rs.moveToNext()){
            val id = rs.getInt(0)
            val tenTruyen = rs.getString(1)
            val noiDung = rs.getString(2)
            val anh = rs.getString(3)
            val id_tk = rs.getInt(4)

            val truyen = Truyen(id,tenTruyen,noiDung,anh,id_tk)
            ds.add(truyen)
        }
        val adapter = AdapterTruyen2(this, ds)
        binding.listViewNew.adapter = adapter
        binding.listViewNew.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, ManNoiDung::class.java)

            val tenT = ds[i].getTenTruyen()
            val noiDung = ds[i].getNoiDung()
            intent.putExtra("tenTruyen", tenT)
            intent.putExtra("noiDung", noiDung)
            startActivity(intent)

            adapter.notifyDataSetChanged()
        }
    }


    //thanh actionBar với toolbar
    private fun actionBar(){
        //hàm hỗ trợ toolbar
        setSupportActionBar(binding.toolbarManHinhChinh)

        //set nút quay la cho actionBar
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarManHinhChinh.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size)

        binding.toolbarManHinhChinh.setNavigationOnClickListener {
            //mở thanh menu từ bên trái
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

    }

    //phương thức cho chạy quảng cáo với viewFlipper
    private fun actionViewFlipper() {
        //mảng chứa các ảnh cho quảng cáo
        var mangQuangCao = arrayListOf<String>()
        mangQuangCao.add("https://demoda.vn/wp-content/uploads/2022/01/anh-rua-va-tho.jpg")
        mangQuangCao.add("https://nuoicondung.com/wp-content/uploads/2022/06/Truye%CC%A3%CC%82n-Cu%CC%89-Ca%CC%89i-Tra%CC%86%CC%81ng.jpg")
        mangQuangCao.add("https://sachhay24h.com/uploads/files/hai-con-de-qua-cau.jpg")
        mangQuangCao.add("https://thegioicotich.vn/wp-content/uploads/2022/03/cau-chuyen-chu-be-chan-cuu.jpg")

        //thực hiện vòng lặp for gán ảnh vào imageView rồi lên app
        for(i in mangQuangCao.indices){
            val imageView = ImageView(applicationContext)
            //sử dụng thư viện picasso
            Picasso.get().load(mangQuangCao[i]).into(imageView)
            //chỉnh tấm hình vừa khung quảng cáo
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            //thêm ảnh từ imageView vào viewFlipper
            binding.viewFlipper.addView(imageView)
        }
        //Thiết lập tự động chạy cho viewFlipper chạy trong 3s
        binding.viewFlipper.flipInterval = 4000
        //tự động chạy khi mở màn hình
        binding.viewFlipper.isAutoStart = true

        //gọi animation ra
        val animation1 = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_in_right)
        val animation2 = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_out_right)

        //gọi animation vào Flipper
        binding.viewFlipper.setInAnimation(animation1)
        binding.viewFlipper.setOutAnimation(animation2)
    }

    //thêm menu timKiem vao Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, TimKiem::class.java)
        when(item.itemId){
            R.id.menu1 -> startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}