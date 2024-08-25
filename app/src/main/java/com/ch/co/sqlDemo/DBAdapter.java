package com.ch.co.sqlDemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBAdapter {
    private static final String DB_NAME = "book.db";
    private static final String DB_TABLE = "bookinfo";
    private static final int DB_VERSION = 1;
    public static final String ID = "_id";
    public static final String BOOK_NAME = "bookName";
    public static final String AUTHOR = "author";
    public static final String BOOK_PRICE = "bookPrice";
    private SQLiteDatabase db;
    private final Context context;
    private BookDBHelper dbHelper;

    public DBAdapter(Context _context) {
        context = _context;
    }

    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    public void open() throws SQLiteException {
        dbHelper = new BookDBHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    public long insert(Book book) {
        ContentValues bookValues = new ContentValues();
        bookValues.put(BOOK_NAME, book.getBookName());
        bookValues.put(AUTHOR, book.getAuthor());
        bookValues.put(BOOK_PRICE, book.getBookPrice());
        long ret = db.insert(DB_TABLE, null, bookValues);
        return ret;
    }

    public Book[] queryAll() {
        Cursor results = db.query(DB_TABLE, new String[]{ID, BOOK_NAME, AUTHOR, BOOK_PRICE}, null, null, null, null, null);
        return ConvertToBook(results);
    }

    public Book[] queryOne(long id) {
        Cursor results = db.query(DB_TABLE, new String[]{ID, BOOK_NAME, AUTHOR, BOOK_PRICE}, ID + "=" + id, null, null, null, null);
        return ConvertToBook(results);
    }

    public Book[] ConvertToBook(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }

        Book[] bookList = new Book[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            bookList[i] = new Book();
            int newId = cursor.getInt(0);
            bookList[i].setId(newId);
            String newBookName = cursor.getString(cursor.getColumnIndex(BOOK_NAME));
            bookList[i].setBookName(newBookName);
            String newAuthor = cursor.getString(cursor.getColumnIndex(AUTHOR));
            bookList[i].setAuthor(newAuthor);
            Float newBookPrice = cursor.getFloat(cursor.getColumnIndex(BOOK_PRICE));
            bookList[i].setBookPrice(newBookPrice);
            cursor.moveToNext();
        }
        return bookList;
    }

    public long deleteAll() {
        return db.delete(DB_TABLE, null, null);
    }

    public long deleteOne(long id) {
        return db.delete(DB_TABLE, ID + "=" + id, null);
    }

    public long updateOne(long id, Book book) {
        ContentValues bookValues = new ContentValues();
        bookValues.put(BOOK_NAME, book.getBookName());
        bookValues.put(AUTHOR, book.getAuthor());
        bookValues.put(BOOK_PRICE, book.getBookPrice());
        return db.update(DB_TABLE, bookValues, ID + "=" + id, null);
    }

    private static class BookDBHelper extends SQLiteOpenHelper {

        public BookDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        private static final String DB_CREATE =
                "create table " + DB_TABLE + "(" + ID + " integer primary key autoincrement, "
                        + BOOK_NAME + " text not null, " + AUTHOR + " text, " + BOOK_PRICE + " float);";

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {

        }

        @Override
        public SQLiteDatabase getReadableDatabase() {
            return super.getReadableDatabase();
        }

        @Override
        public SQLiteDatabase getWritableDatabase() {
            return super.getWritableDatabase();
        }


        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public synchronized void close() {
            super.close();
        }
    }

}
