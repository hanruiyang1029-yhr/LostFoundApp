package com.example.lostfoundapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "LostFound.db";

    public static final String TABLE_NAME = "items";

    public DBHelper(@Nullable Context context) {
        super(context,
                DATABASE_NAME,
                null,
                1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "type TEXT," +
                        "name TEXT," +
                        "phone TEXT," +
                        "description TEXT," +
                        "date TEXT," +
                        "location TEXT," +
                        "category TEXT," +
                        "timestamp TEXT" +
                        ")";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {

        db.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_NAME
        );

        onCreate(db);
    }

    public boolean insertItem(String type,
                              String name,
                              String phone,
                              String description,
                              String date,
                              String location,
                              String category,
                              String timestamp) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put("type", type);

        values.put("name", name);

        values.put("phone", phone);

        values.put("description", description);

        values.put("date", date);

        values.put("location", location);

        values.put("category", category);

        values.put("timestamp", timestamp);

        long result =
                db.insert(TABLE_NAME,
                        null,
                        values);

        return result != -1;
    }

    public ArrayList<Item> getAllItems() {

        ArrayList<Item> itemList =
                new ArrayList<>();

        SQLiteDatabase db =
                this.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT * FROM " + TABLE_NAME,
                        null
                );

        if (cursor.moveToFirst()) {

            do {

                Item item =
                        new Item(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                cursor.getString(7),
                                cursor.getString(8)
                        );

                itemList.add(item);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return itemList;
    }

    public void deleteItem(int id) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        db.delete(TABLE_NAME,
                "id=?",
                new String[]{
                        String.valueOf(id)
                });
    }
}
