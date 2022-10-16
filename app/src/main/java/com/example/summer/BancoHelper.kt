package com.example.summer


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class BancoHelper (var context: Context): SQLiteOpenHelper(context, "summer.db", null, 2) {

    override fun onCreate(db: SQLiteDatabase) {
        val sql = "create table itens(id integer primary key autoincrement, nome text, preco real)"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, anterior: Int, atual: Int) {
        var query: String = "DROP TABLE IF EXISTS itens"
        db.execSQL(query)
        onCreate(db)
    }
}