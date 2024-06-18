package com.dinhthikimthoa.de02_onthi.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class ProductDB extends SQLiteOpenHelper {
    public static final String DB_NAME = "Product.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "product";
    public static final String P_ID = "PId";
    public static final String P_NAME = "PName";
    public static final String P_PRICE = "PPrice";

    public ProductDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("+
                    P_ID + " NVARCHAR(50) PRIMARY KEY, " +
                    P_NAME + " NVARCHAR(50),"+
                    P_PRICE + " REAL) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME );
        onCreate(db);
    }

    public Cursor getData(String sql){
        Cursor cursor = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            return db.rawQuery(sql,null);
        }catch (Exception ex){
            return cursor;
        }
    }

    public boolean execSql (String sql){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
    public int getNumbOfRows (){
        Cursor cursor = getData("SELECT * FROM "+ TABLE_NAME);
        int numbOfRow = cursor.getCount();
        cursor.close();
        return numbOfRow;
    }

    public boolean deleteData(String code ){
        try{
            SQLiteDatabase db = getWritableDatabase();
            String sql ="DELETE FROM " + ProductDB.TABLE_NAME + " WHERE " + P_ID + "= ?" ;
            SQLiteStatement statement = db.compileStatement(sql);
            statement.clearBindings();
            statement.bindString(1, code);
            statement.executeUpdateDelete();
            return true;
        }catch (Exception e){
            return  false;
        }

    }
    public boolean  insertData(String id, String name, double price){
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "INSERT INTO " + TABLE_NAME + " VALUES( ?,?,?) ";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.clearBindings();
            statement.bindString(0,id);
            statement.bindString(1,name);
            statement.bindDouble(2,price);
            statement.executeInsert();
            return true;
        }catch (Exception ex) {
            return false;
        }
    }
    public void CreateSampleData(){
        if (getNumbOfRows() == 0){
            execSql("INSERT INTO "+ TABLE_NAME + " VALUES('SP-123', 'IPhone 5S', 23000000)");
            execSql("INSERT INTO "+ TABLE_NAME + " VALUES('SP-124', 'Vertu ConStellation', 20000000)");
            execSql("INSERT INTO "+ TABLE_NAME + " VALUES('SP-125', 'Nokia Lumia 925', 2300000)");
            execSql("INSERT INTO "+ TABLE_NAME + " VALUES('SP-126', 'Samsung Galaxy S4', 1900000)");
            execSql("INSERT INTO "+ TABLE_NAME + " VALUES('SP-127', 'HTC Desesire 600', 2200000)");
            execSql("INSERT INTO "+ TABLE_NAME + " VALUES('SP-128', 'HKPhone Revo LEAD', 24000000)");
        }
    }

}
