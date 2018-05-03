package com.nddcoder.quanlybaihat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BaiHatDB extends SQLiteOpenHelper {

    private String tableName = "tbl_baihat";
    private String col_maBH = "maBH";
    private String col_tenBH = "tenBH";
    private String col_tenCaSi = "tenCS";
    private String col_thoiLuong = "thoiLuong";

    public BaiHatDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = String.format("Create table if not exists %s (%s Text Primary key, %s Text,%s Text,%s Text);", tableName, col_maBH, col_tenBH, col_tenCaSi, col_thoiLuong);

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + tableName);
        //Tạo lại
        onCreate(db);
    }

    public void themBaiHat(BaiHat baiHat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_maBH, baiHat.getMaBaiHat());
        values.put(col_tenBH, baiHat.getTenBaiHat());
        values.put(col_tenCaSi, baiHat.getTenCaSi());
        values.put(col_thoiLuong, baiHat.getThoiLuong());

        db.insert(tableName, null, values);
        db.close();
    }

    public void suaBaiHat(BaiHat baiHat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_maBH, baiHat.getMaBaiHat());
        values.put(col_tenBH, baiHat.getTenBaiHat());
        values.put(col_tenCaSi, baiHat.getTenCaSi());
        values.put(col_thoiLuong, baiHat.getThoiLuong());

        db.update(tableName, values, col_maBH + "=?", new String[]{baiHat.getMaBaiHat()});
        db.close();
    }

    public void xoaBaiHat(String maBH) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + tableName + " where " + col_maBH + " = " + maBH + ";";
        db.execSQL(sql);
        db.close();
    }

    public List<BaiHat> layTatCaBaiHat() {
        List<BaiHat> dsBaiHat = new ArrayList<>();
        String sql = "select * from " + tableName + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                BaiHat bh = new BaiHat(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                        );
                dsBaiHat.add(bh);
            }
        }
        return dsBaiHat;
    }
}
