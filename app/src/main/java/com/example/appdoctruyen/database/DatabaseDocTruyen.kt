package com.example.appdoctruyen.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appdoctruyen.model.TaiKhoan
import com.example.appdoctruyen.model.Truyen

lateinit var db:SQLiteDatabase
lateinit var rs:Cursor
class DatabaseDocTruyen(val context: Context):SQLiteOpenHelper(context, "DOCTRUYEN", null, 1) {
    //bảng tài khoản
    val TABLE_TAIKHOAN = "taikhoan"
    val ID_TAIKHOAN = "idtaikhoan"
    val TEN_TAIKHOAN = "tentaikhoan"
    val MATKHAU = "matkhau"
    val PHAN_QUYEN = "phanquyen"
    val EMAIL = "email"

    //bảng truyện
    val TABLE_TRUYEN = "truyen"
    val ID_TRUYEN = "idtruyen"
    val TEN_TRUYEN = "tieude"
    val NOIDUNG = "noidung"
    val ANH = "anh"

    //câu lệnh SQL
    val sqlQuery = "create table "+TABLE_TAIKHOAN+" ( "+ID_TAIKHOAN+" integer primary key autoincrement, "+
            TEN_TAIKHOAN + " text unique, "+
            MATKHAU +" text, "+
            EMAIL+" text, "+
            PHAN_QUYEN +" integer)"
    val sqlQuery1 = "create table "+TABLE_TRUYEN+ "( "+ID_TRUYEN+ " integer primary key autoincrement, "+
            TEN_TRUYEN+ " text unique, "+
            NOIDUNG+ " text,"+
            ANH+ " text, "+
            ID_TAIKHOAN+ " integer,"+
            " foreign key ( "+ID_TAIKHOAN+" ) references "+ TABLE_TAIKHOAN+"("+ID_TAIKHOAN+"))"

    val sqlQuery2 = "insert into taikhoan values(null, 'admin', 'admin', 'admin@gmail.com', 2)"
    val sqlQuery3 = "insert into taikhoan values(null, 'huy', 'huy', 'huy@gmail.com', 1)"

    val sqlQuery4 = "insert into truyen values (null, 'Chú bé chăn cừu','Có một chú bé chăn cừu thường thả cừu gần chân núi. Một hôm, thấy buồn quá, chú nghĩ ra một trò đùa cho vui. Chú giả vờ kêu toáng lên:" + "\n" +
            "– Sói! Sói! Cứu tôi với!" + "\n" +
            "Nghe tiếng kêu cứu, mấy bác nông dân đang làm việc gần đấy tức tốc chạy tới. Nhưng họ không thấy sói đâu. Thấy vậy, chú khoái chí lắm." + "\n" +
            "Mấy hôm sau, chú lại bày ra trò ấy. Các bác nông dân lại chạy tới. Rồi một hôm, sói đến thật. Chú hốt hoảng kêu gào xin cứu giúp. Các bác nông dân nghĩ là chú lại lừa mình, nên vẫn thản nhiên làm việc. Thế là sói thỏa thuê ăn thịt hết cả đàn cừu.',"+"\n"+
            "'https://thegioicotich.vn/wp-content/uploads/2022/03/cau-chuyen-chu-be-chan-cuu.jpg', '1')"

    val sqlQuery5 = "insert into truyen values(null, 'Rùa và thỏ', 'Ngày xửa ngày xưa, có một con Rùa và một con Thỏ cãi nhau xem ai nhanh hơn. " +"\n"+
            "Chúng quyết định giải quyết việc tranh luận bằng một cuộc thi chạy đua. Chúng đồng ý lộ trình và bắt đầu cuộc đua.)"+"\n"+
            "Thỏ xuất phát nhanh như tên bắn và chạy thục mạng rất nhanh, khi thấy rằng mình đã khá xa Rùa, Thỏ nghĩ nên nghỉ cho đỡ mệt dưới một bóng cây xum xê lá bên vệ đường và nghỉ thư giãn trước khi tiếp tục cuộc đua." +"\n"+
            "Vì quá tự tin vào khả năng của mình, Thỏ ngồi dưới bóng cây và nhanh chóng ngủ thiếp đi trên đường đua. Rùa từ từ vượt qua Thỏ và sớm kết thúc đường đua.\n"+
            "Khi Thỏ thức dậy thì rùa đã đến đích và trở thành người chiến thắng. Thỏ giật mình tỉnh giấc và nhận ra rằng nó đã bị thua.',"+
            "'https://demoda.vn/wp-content/uploads/2022/01/anh-rua-va-tho.jpg', '1')"

    val sqlQuery6 = "insert into truyen values(null, 'Củ cải trắng','Mùa đông đã đến rồi trời lạnh buốt, Thỏ con không có gì để ăn cả. Thỏ con mặc áo vào rồi ra ngoài kiếm thức ăn. Nó đi mãi đi mãi cuối cùng cũng tìm được 2 củ cải trắng. Thỏ con reo lên:"+"\n"+
            "– Ôi, ở đây có hai củ cải trắng liền, mình thật là may mắn!"+"\n"+
            "Thỏ con đói bụng, muốn ăn lắm rồi. Nhưng Thỏ lại nghĩ:"+"\n"+
            "– Ừm… trời lạnh thế này, chắc Dê con cũng không có cái gì để ăn đâu. Mình phải mang cho Dê con một củ mới được."+"\n"+
            "Thế là Thỏ con đi sang nhà bạn Dê nhưng Dê con không có nhà nên Thỏ đặt củ cải lên bàn rồi đi về." +"\n"+
            "Tình cờ, Dê con đi chơi cũng tìm được một củ cải trắng nhưng nó chỉ ăn trước một nửa." +"\n"+
            "Về đến nhà, lại thấy có một củ cải trắng ở trên bàn Dê thèm ăn lắm, nhưng lại nghĩ:" +"\n"+
            "– Ôi trời lạnh thế này chắc Hươu con không có cái gì để ăn rồi, mình phải mang cho Hươu con mới được." +"\n"+
            "Dê con đến nhà Hươu nhưng Hươu lại đi vắng, Dê con bèn đặt củ cải ở trên bàn rồi về." +"\n"+
            "Khi Hươu về nhà, thấy củ cải ở trên bàn, Hươu ngạc nhiên lắm." +"\n"+
            "– Ồ, củ cải trắng ở đâu mà ngon vậy nhỉ. Xuỵt… thích quá. Nhưng chắc trời lạnh thế này, Thỏ con cũng không có gì ăn đâu. Mình phải mang sang cho Thỏ mới được." +"\n"+
            "Khi Hươu đến thì Thỏ con đang ngủ rất say. Khi tỉnh dậy Thỏ lại thấy trên bàn mình xuất hiện một củ cải trắng.Thỏ vui lắm nó chạy đi gọi các bạn:" +"\n"+
            "– Bạn Hươu ơi, bạn Dê ơi hãy đến nhà tôi, chúng ta cùng ăn củ cải trắng thơm ngon này." +"\n"+
            "Thế là cuối cùng, củ cải trắng ấy được chia sẻ cho cả ba người bạn tốt bụng của chúng ta. Các bạn thấy đấy tấm lòng thơm thảo, sẵn sàng sẻ chia của các bạn ấy thật là đáng học tập phải không nào?',"+"\n"+
            "'https://nuoicondung.com/wp-content/uploads/2022/06/Truye%CC%A3%CC%82n-Cu%CC%89-Ca%CC%89i-Tra%CC%86%CC%81ng.jpg','1')"

    val sqlQuery7 = "insert into truyen values(null, 'Dê đen và dê trắng', 'Dê đen và Dê trắng cùng sống trong một khu rừng. Hàng ngày, cả hai thường đến uống nước và tìm cái ăn ở trong khu rừng quen thuộc." +"\n"+
            "Một hôm, Dê trắng đi tìm cái ăn và uống nước suối như mọi khi. Dê đang mải mê ngặm cỏ, bất chợt một con Sói ở đâu nhảy xổ ra." +"\n"+
            "Sói quát hỏi:- Dê kia! mi đi đâu?" +"\n"+
            "Dê trắng sợ rúm cả người, lắp bắp:" +"\n"+
            "- Dạ, dạ, tôi đi tìm... tìm cỏ non và...và uống nước suối ạ!" +"\n"+
            "- Mi có gì ở chân?" +"\n"+
            "- Dạ, dạ, chân của tôi có móng ạ...ạ!" +"\n"+
            "- Trên đầu mi có gì?" +"\n"+
            "- Dạ, dạ, trên đầu tôi có đôi sừng mới nhú...Sói càng quát to hơn:" +"\n"+
            "- Trái tim mi thế nào?" +"\n"+
            "- Ôi, ôi, trái...trái tim tôi đang run sợ...sợ..." +"\n"+
            "Sói cười vang rồi ăn thịt chú Dê trắng tội nghiệp.Dê đen cũng đi tới khu rừng để ăn cỏ non và uống nước suối. " +"\n"+
            "Đang tha thẩn ngặm cỏ, chợt Sói xuất hiện, nó quát hỏi:"+"\n"+
            " Dê kia, mi đi đâu?" + "\n" +
            "Dê đen nhìn con Sói từ đầu tới chân rồi ngước cổ trả lời:" + "\n" +
            "- Ta đi tìm kẻ nào thích gây sự đây! Sói bị bất ngờ, nó hỏi tiếp:" + "\n" +
            "- Thế dưới chân mi có gì?" + "\n" +
            "- Chân thép của ta có móng bằng đồng." + "\n" +
            "- Thế...thế...trên đầu mi có gì?" + "\n" +
            "- Trên đầu của ta có đôi sừng bằng kim cương!" + "\n" +
            "Sói sợ lắm rồi, nhưng vẫn cố hỏi:" + "\n" +
            "- Mi...mi...trái tim mi thế nào?" + "\n" +
            "Dê đen dõng dạc trả lời:" + "\n" +
            "- Trái tim thép của ta bảo ta rằng: hãy cắm đôi sừng kim cương vào đầu Sói. Nào, Sói hãy lại đây." + "\n" +
            "Ôi trời, sợ quá, con Sói ba chân bốn cẳng chạy biến vào rừng, từ đó không ai trông thấy nó lởn vởn ở khu rừng đó nữa.',"+ "\n" +
            "'https://sachhay24h.com/uploads/files/hai-con-de-qua-cau.jpg','1')"


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(sqlQuery)
        db?.execSQL(sqlQuery1)
        db?.execSQL(sqlQuery2)
        db?.execSQL(sqlQuery3)
        db?.execSQL(sqlQuery4)
        db?.execSQL(sqlQuery5)
        db?.execSQL(sqlQuery6)
        db?.execSQL(sqlQuery7)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    //lấy tất cả tài khoản
    fun getData():Cursor{
        db = this.readableDatabase
        rs = db.rawQuery("select * from $TABLE_TAIKHOAN",null)

        return rs
    }


    fun insertData(taiKhoan:TaiKhoan){
        db = this.writableDatabase

        var cv = ContentValues()

        cv.put("tentaikhoan", taiKhoan.getTenTaiKhoan())
        cv.put("matkhau", taiKhoan.getMatKhau())
        cv.put("email", taiKhoan.getEmail())
        cv.put("phanquyen", taiKhoan.getPhanQuyen())

        db.insert("taikhoan", null, cv)

        db.close()
    }

    //lấy 3 truyện mới nhất
    fun getTruyen():Cursor{
        db = this.readableDatabase
        rs = db.rawQuery("select * from truyen order by idtruyen desc limit 3", null)

        return rs
    }

    fun getData2():Cursor{
        db = this.readableDatabase
        rs = db.rawQuery("select * from $TABLE_TRUYEN",null)

        return rs
    }

    //add truyen
    fun addTruyen(truyen:Truyen){
        db = this.writableDatabase
        var cv = ContentValues()
        cv.put("tieude", truyen.getTenTruyen())
        cv.put("noidung", truyen.getNoiDung())
        cv.put("anh", truyen.getAnh())
        cv.put("idtaikhoan", truyen.getId_tk())

        db.insert("truyen", null, cv)
        db.close()
    }

    fun deleteTruyen(id:Int):Int{
        db = this.readableDatabase
        var res = db.delete("truyen", "idtruyen =$id", null)
        return res
    }
}