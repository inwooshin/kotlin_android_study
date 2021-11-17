package com.inwooshin.file_database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

data class Memo(var no:Long?, var content:String, var datetime:Long)

//name - 들어오는 file
class SqliteHelper(context: Context, name:String, version:Int) :SQLiteOpenHelper(context, name, null, version) {
    //SQLite 헬퍼를 호출하면 파일이름이 생성되어있지 않으면 안드로이드가 OnCreate 함수를 호출

    override fun onCreate(db: SQLiteDatabase?) {
        //create table 이 명령어 memo 는 이름명칭, 괄호안에는 컬럼 정의
        //primary key 가 붙으면 자동으로 1,2,3,4,5 이렇게 쭉쭉 들어간다.
        val create = "create table memo (`no` integer primary key, content text, datetime integer)"
        db?.execSQL(create)
    }

    //생성되어있으나 버전이 다르면 onUpgrade 함수를 호출
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //테이블에 변경사항이 있을 경우에 호출된다.
        //SqliteHelper() 의 생성자를 호출할 때 기존 데이터베이스의 version 을 비교해
        //더 높으면 호출

    }

    //데이터 입력함수
    fun insertMemo(memo: Memo){
        //SQLiteOpenHelper 의 기능을 다 쓸수 있다.

        //db 가져오기
        val wd = writableDatabase
        //Menu를 입력타입으로 변환해줌
        val values = ContentValues()
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)

        //db에 넣기
        wd.insert("memo", null, values)

        //db 닫기
        wd.close()
    }

    //데이터 조회함수
    fun selectMemo() : MutableList<Memo>{
        val list = mutableListOf<Memo>()

        //val select = "select no, content, datetime from memo"
        val select = "select * from memo"
        val rd = readableDatabase

        //query 를 만들어서 실행하는 두가지 방법
        //아래는 db?.execSQL(create) 얘와 달리 반환값을 가진다
        //cursor 는 일종의 list 같은 것이다!
        val cursor = rd.rawQuery(select, null)

        //여러줄의 데이터를 긁어와서 첫번째줄을 가리킴
        while(cursor.moveToNext()){
            //0이 아닌 -1의 값을 리턴할 수도 있기 때문에 getColumnIndexOrThrow 메서드 사용
            val no = cursor.getLong(cursor.getColumnIndexOrThrow("no"))
            val content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
            val datetime = cursor.getLong(cursor.getColumnIndexOrThrow("datetime"))

            val memo = Memo(no, content, datetime)
            list.add(memo)
        }
        cursor.close()
        rd.close()

        return list
    }

    fun updateMemo(memo:Memo){
        val wd = writableDatabase

        val values = ContentValues()
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)

        //memo 에 no 에 해당하는 부분을 수정해라 values 로
        wd.update("memo", values, "no = ${memo.no}", null)
        wd.close()
    }

    fun deleteMemo(memo:Memo){
        val delete = "delete from memo where no = ${memo.no}"
        val wd = writableDatabase
        wd.execSQL(delete)

        wd.delete("memo", "no = ${memo.no}", null)
        wd.close()
    }
}