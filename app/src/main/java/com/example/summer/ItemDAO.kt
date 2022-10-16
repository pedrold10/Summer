package com.example.summer


import android.content.ContentValues
import android.content.Context

class ItemDAO {
    val banco: BancoHelper

    constructor(context: Context){
        this.banco = BancoHelper(context)
    }

    fun insert(item: Item){
        val cv = ContentValues()
        cv.put("item", item.nome)
        cv.put("preco", item.preco)
        this.banco.writableDatabase.insert("itens", null, cv)
    }

    fun read(): ArrayList<Item>{
        val lista = arrayListOf<Item>()
        val colunas = arrayOf("id", "nome", "preco")
        val c = this.banco.readableDatabase.query("pessoas", colunas, null, null, null, null, "nome")
        c.moveToFirst()
        for (i in 1 .. c.count){
            val id = c.getInt(0)
            val nome = c.getString(1)
            val preco = c.getDouble(2)
            val item = Item(id, nome, preco)
            lista.add(item)
            c.moveToNext()
        }
        return lista
    }

    fun find(id: Int): Item?{
        return null
    }

    fun delete(id: Int){
        val where = arrayOf(id.toString())
        this.banco.writableDatabase.delete("itens", "id = ?", where)
    }

    fun delete(pessoa: Item){
        this.delete(pessoa.id)
    }

    fun update(pessoa: Item){

    }
}