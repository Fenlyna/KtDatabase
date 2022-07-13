package com.amia.latihanuas

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DB_NAME = "kampus.db"
const val VERSION = 1

class DatabaseHelper(
    context: Context
) : SQLiteOpenHelper(
    context,
    DB_NAME,
    null,
    VERSION,
) {

    override fun onCreate(db: SQLiteDatabase?) {
        val tableMahasiswa = "CREATE TABLE mahasiswa (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nama TEXT NOT NULL, " +
                " kelas TEXT NOT NULL, " +
                " nrp TEXT NOT NULL, " +
                " nilai INTEGER NOT NULL" +
                ")"
        db?.execSQL(tableMahasiswa)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS mahasiswa")
        this.onCreate(db)
    }
}