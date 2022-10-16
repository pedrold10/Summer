package com.example.summer


import java.util.*

class Item {
    var id: Int
    var nome: String
    var preco: Double

    // memória
    constructor(nome: String, preco: Double){
        this.id = -1
        this.nome = nome
        this.preco = preco
    }

    //banco
    constructor(id: Int, nome: String, preco: Double){
        this.id = id
        this.nome = nome
        this.preco = preco

    }

    private fun precoReal(): String{
        /*
        val dia = this.data.get(Calendar.DAY_OF_MONTH)
        val mes = this.data.get(Calendar.MONTH) + 1
        val ano = this.data.get(Calendar.YEAR)
        val hora = this.data.get(Calendar.HOUR_OF_DAY)
        val minuto = this.data.get(Calendar.MINUTE)
        val segundo = this.data.get(Calendar.SECOND)*/
        val precoStr = preco.toString().replace(".", ",")
        val precoFormatado = "R$${precoStr}"
        return precoFormatado
    }

}