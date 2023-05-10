package com.example.appdoctruyen.model

class Truyen {
    private var id_truyen:Int=0
    private var tenTruyen:String =""
    private var noiDung:String=""
    private var anh:String=""
    private var id_tk:Int =0

    constructor(id:Int,tenTruyen:String, noiDung:String, anh:String, id_tk:Int){
        this.id_truyen = id
        this.tenTruyen = tenTruyen
        this.noiDung = noiDung
        this.anh = anh
        this.id_tk = id_tk
    }

    constructor(tenTruyen:String, noiDung:String, anh:String, id_tk:Int){
        this.tenTruyen = tenTruyen
        this.noiDung = noiDung
        this.anh = anh
        this.id_tk = id_tk
    }


    fun getId_truyen():Int{
        return this.id_truyen
    }
    fun getTenTruyen():String{
        return this.tenTruyen
    }
    fun getNoiDung():String{
        return this.noiDung
    }
    fun getAnh():String{
        return this.anh
    }
    fun getId_tk():Int{
        return this.id_tk
    }

}