package com.example.projectvasco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

// Database SQLite untuk data transaksi (saldo dihitung dari transaksi).
public class WalletDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dompetku.db";
    private static final int DB_VERSION = 1;

    public WalletDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE transactions ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "type TEXT NOT NULL,"
                + "title TEXT NOT NULL,"
                + "amount INTEGER NOT NULL,"
                + "created_at INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS transactions");
        onCreate(db);
    }

    public void insert(String type, String title, long amount) {
        ContentValues cv = new ContentValues();
        cv.put("type", type);
        cv.put("title", title);
        cv.put("amount", amount);
        cv.put("created_at", System.currentTimeMillis());
        getWritableDatabase().insert("transactions", null, cv);
    }

    public long getBalance() {
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT COALESCE(SUM(CASE WHEN type='TOPUP' THEN amount ELSE -amount END), 0) "
                        + "FROM transactions", null);
        long balance = 0;
        if (c.moveToFirst()) balance = c.getLong(0);
        c.close();
        return balance;
    }

    public List<Transaction> getAll() {
        List<Transaction> list = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT type, title, amount, created_at FROM transactions ORDER BY id DESC", null);
        while (c.moveToNext()) {
            list.add(new Transaction(c.getString(0), c.getString(1),
                    c.getLong(2), c.getLong(3)));
        }
        c.close();
        return list;
    }
}