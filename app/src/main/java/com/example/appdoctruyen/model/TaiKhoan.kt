package com.example.appdoctruyen.model

class TaiKhoan {
    private var mid:Int = 0
    private var mTenTaiKhoan:String = ""
    private var mMatKhau:String = ""
    private var mEmail:String = ""
    private var mPhanQuyen:Int = 0
    constructor(mid:Int, mTenTaiKhoan:String, mMatKhau:String, mEmail:String, mPhanQuyen:Int){
        this.mid = mid
        this.mTenTaiKhoan = mTenTaiKhoan
        this.mMatKhau = mMatKhau
        this.mEmail = mEmail
        this.mPhanQuyen = mPhanQuyen
    }

    constructor(mTenTaiKhoan:String, mMatKhau:String, mEmail:String, mPhanQuyen:Int){
        this.mTenTaiKhoan = mTenTaiKhoan
        this.mMatKhau = mMatKhau
        this.mEmail = mEmail
        this.mPhanQuyen = mPhanQuyen
    }

    constructor(mTenTaiKhoan:String, mEmail:String){
        this.mTenTaiKhoan = mTenTaiKhoan
        this.mEmail = mEmail
    }

    fun getmId():Int{
        return this.mid
    }

    fun getTenTaiKhoan():String{
        return this.mTenTaiKhoan
    }

    fun getMatKhau():String{
        return this.mMatKhau
    }
    fun getEmail():String{
        return this.mEmail
    }
    fun getPhanQuyen():Int{
        return this.mPhanQuyen
    }

    fun setmId(mid: Int){
        this.mid = mid
    }

    fun setTenTaiKhoan(ten:String){
        this.mTenTaiKhoan = ten
    }

    fun setMatKhau(ten:String){
        this.mMatKhau = ten
    }

    fun setEmail(ten:String){
        this.mEmail = ten
    }

    fun setPhanQuyen(ten:Int){
        this.mPhanQuyen = ten
    }

}