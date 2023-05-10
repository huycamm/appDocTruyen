package com.example.appdoctruyen.model

class ChuyenMuc {
    var tenChuyenMuc:String = ""
    var hinhAnhChuyenMuc:Int = 0

    constructor(ten:String, hinhAnh:Int){
        this.tenChuyenMuc = ten
        this.hinhAnhChuyenMuc = hinhAnh
    }

    fun getChuyenMuc():String{
        return this.tenChuyenMuc
    }

    fun getHinhAnh():Int{
        return this.hinhAnhChuyenMuc
    }
}