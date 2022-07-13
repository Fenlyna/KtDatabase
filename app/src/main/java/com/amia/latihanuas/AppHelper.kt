package com.amia.latihanuas

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class AppHelper(private val database: SQLiteDatabase) {
    fun insertData(entity: MahasiswaEntity): Long {
        val values = ContentValues()
        values.put(MahasiswaColumn.COLUMN_NAMA, entity.nama)
        values.put(MahasiswaColumn.COLUMN_NRP, entity.nrp)
        values.put(MahasiswaColumn.COLUMN_KELAS, entity.kelas)
        values.put(MahasiswaColumn.COLUMN_NILAI, entity.nilai)

        return database.insert(MahasiswaColumn.TABLE_NAME, null, values)
    }

    fun lihatSeluruhData(): List<MahasiswaEntity> {
        val listData: ArrayList<MahasiswaEntity> = arrayListOf()

        val query = "SELECT * FROM " + MahasiswaColumn.TABLE_NAME

        lateinit var cursor: Cursor
        try {
            cursor = database.rawQuery(query, null)
            if (cursor.moveToFirst()) {
                do {
                    val entity = MahasiswaEntity(
                        id = cursor.getInt(0),
                        nama = cursor.getString(1),
                        kelas = cursor.getString(2),
                        nrp = cursor.getString(3),
                        nilai = cursor.getInt(4)
                    )
                    listData.add(entity)
                }while (
                    cursor.moveToNext()
                )
            }
        } finally {
            cursor.close()
        }
        return listData
    }
    fun deleteData(mahasiswaEntity: MahasiswaEntity) {
        val query = "DELETE FROM " + MahasiswaColumn.TABLE_NAME + " WHERE id=" + mahasiswaEntity.id.toString()

        database.execSQL(query)
    }
}