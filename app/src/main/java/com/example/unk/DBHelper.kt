package com.example.unk


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context,Utils.DATABASE_NAME, factory, Utils.DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + Utils.TABLE_NAME + " ("
        + Utils.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," +
        Utils.NAME_COl + " TEXT," +
        Utils.Num_COL + " TEXT" + ")")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS" + Utils.TABLE_NAME)
        onCreate(db)
    }
//    fun deleteAllContacts(): Int {
//        val db = writableDatabase
//        val rowsAffected = db.delete(TABLE_NAME, null, null)
//        db.close()
//        return rowsAffected
//    }
//
    fun addContact(name: String, number: String): Long {
        val values = ContentValues().apply {
            put(Utils.NAME_COl, name)
            put(Utils.Num_COL, number)
        }

        val db = writableDatabase
        val id = db.insert(Utils.TABLE_NAME, null, values)
        db.close()
        return id
    }
//
//    fun getContact(id: Long): Cursor? {
//        val db = readableDatabase
//        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $ID_COL = ?", arrayOf(id.toString()))
//    }
//
//    fun getAllContacts(): Cursor? {
//        val db = readableDatabase
//        return db.rawQuery("SELECT $ID_COL, $NAME_COL, $NUMBER_COL FROM $TABLE_NAME", null)?.apply {
//            moveToFirst() // Move to the first row of the cursor
//        }
//    }
//
    fun updateContact(id: Long, name: String, number: String) {
        val values = ContentValues().apply {
            put(Utils.NAME_COl, name)
            put(Utils.Num_COL, number)
        }

        val db = writableDatabase
        db.update(Utils.TABLE_NAME, values, "${Utils.ID_COL}=?", arrayOf(id.toString()))
        db.close()
    }
//
    fun deleteContact(id: Int) {
        val db = this.writableDatabase
        db.delete(Utils.TABLE_NAME, "${Utils.ID_COL}=?", arrayOf(id.toString()))
        db.close()
    }
    fun readAll(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + Utils.TABLE_NAME, null)
    }


}