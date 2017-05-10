package com.example.admin.courseregistration.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.courseregistration.model.SubjectModel;

import java.util.ArrayList;

/**
 * Created by trycatch on 2017. 3. 11..
 */

public class DBHelper extends SQLiteOpenHelper {
    public static DBHelper instance;
    private Context mContext;

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE id" + PreferencesManager.getInstance(mContext).getID() + " (code TEXT, divideClass INTEGER, name TEXT, professor TEXT, grade TEXT, credit TEXT, division TEXT, amount TEXT, note TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(SubjectModel subject) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO id" + PreferencesManager.getInstance(mContext).getID() + " VALUES('" + subject.getCode() + "', '" + subject.getDivideClass() + "', '" + subject.getName() + "', '" + subject.getProfessor() + "', '" + subject.getGrade() + "', '" + subject.getCredit() + "', '" + subject.getDivision() + "', '" + subject.getAmount() + "', '" + subject.getNote() + "');");
        db.close();
    }

    public void delete(SubjectModel subject) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM id" + PreferencesManager.getInstance(mContext).getID() + " WHERE code='" + subject.getCode() + "';");
        db.close();
    }

    public ArrayList<SubjectModel> getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<SubjectModel> subjectArray = new ArrayList<>();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM id" + PreferencesManager.getInstance(mContext).getID(), null);
        while (cursor.moveToNext()) {
            subjectArray.add(new SubjectModel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8)));
        }

        return subjectArray;
    }

    public static DBHelper getInstance(Context context){
        if(instance == null)
            instance = new DBHelper(context, "CourceRegistration.db", null, 1);
        return instance;
    }
}


