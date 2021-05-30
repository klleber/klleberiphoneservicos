package com.example.py7.iphonestore

class Servicos {

    var id: Int? = null
    var nome: String? = null
    var tipo: String? = null
    var preco: String? = null

    constructor(id: Int, nome: String, tipo: String, preco:String){
        this.id = id
        this.nome = nome
        this.tipo = tipo
        this.preco = preco
    }
}