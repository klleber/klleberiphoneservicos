package com.example.py7.iphonestore

import android.content.ContentValues
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_servicos.*

class ServicosActivity : AppCompatActivity() {

    var id=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicos)

        try {
            var bundle: Bundle = intent.extras
            id = bundle.getInt("MainActId", 0)
            if (id !=0){
                txNome.setText(bundle.getString("MainActNome"))
                txModelo.setText(bundle.getString("MainActTipo"))
                txPreco.setText(bundle.getString("MainActPreco"))
            }
        }catch (ex: Exception){
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_menu, menu)

        val itemDelete: MenuItem = menu.findItem(R.id.action_delete)

        if (id ==0){
            itemDelete.isVisible = false
        }else{
            itemDelete.isVisible = true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_save -> {
                var dbAdapter = DBAdapter(this)

                var values = ContentValues()
                values.put("nome", txNome.text.toString())
                values.put("tipo", txModelo.text.toString())
                values.put("preco", txPreco.text.toString())

                if (id == 0){
                    val mID = dbAdapter.insert(values)

                    if (mID > 0){
                        Toast.makeText(this, "Pedido Realizado", Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(this, "Erro ao realizar ao confirmar o serviço", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    var selectionArgs = arrayOf(id.toString())
                    val mID = dbAdapter.update(values, "Id=?", selectionArgs)
                    if (mID > 0){
                        Toast.makeText(this, "Serviço Atualizado", Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(this, "Erro ao atualizar o serviço", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
            R.id.action_delete ->{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Aviso!")
                builder.setMessage("Tem certeza que deseja cancelar o serviço selecionado ?")

                builder.setPositiveButton("Sim, tenho certeza!") {dialog: DialogInterface?, which: Int ->
                    var dbAdapter = DBAdapter(this)
                    val selectionArgs = arrayOf(id.toString())
                    dbAdapter.delete("Id=?", selectionArgs)
                    Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
                    finish()
                }
                builder.setNegativeButton("Não"){dialog: DialogInterface?, which: Int ->  }

                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
