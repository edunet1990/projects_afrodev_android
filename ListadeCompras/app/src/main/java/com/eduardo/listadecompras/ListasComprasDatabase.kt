package com.eduardo.listadecompras

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class ListasComprasDatabase(context : Context) : ManagedSQLiteOpenHelper(ctx = context, name = "listasCompras.db", version = 1) {


    //Sigleton da classe
    companion object{
        private var instance: ListasComprasDatabase? = null


        @Synchronized // protege o método contra o acesso concorrente de multi threads
        fun getInstance(ctx: Context) :ListasComprasDatabase{
            if (instance == null){
                instance = ListasComprasDatabase(ctx.getApplicationContext())
            }
            return instance!!
        }

    }


    //Cria as tabelas do banco de dados
    override fun onCreate(db: SQLiteDatabase) {


        //Criação das tabelas
        db.createTable("produtos",true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "nome" to TEXT,
            "quantidade" to INTEGER,
            "valor" to REAL,
            "foto" to BLOB
        )

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}

//Acesso à propriedade pelo contexto
val Context.database: ListasComprasDatabase
    get() = ListasComprasDatabase.getInstance(getApplicationContext())