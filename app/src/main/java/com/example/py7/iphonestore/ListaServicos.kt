package com.example.py7.iphonestore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView


import kotlinx.android.synthetic.main.activity_lista.*
import kotlinx.android.synthetic.main.content_main.*

class ListaServicos : AppCompatActivity() {

    private var listServicosIos = ArrayList<Servicos>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_lista)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            var intent = Intent(this, ServicosActivity::class.java)
            startActivity(intent)
        }

      loadData()
    }
    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        var dbAdapter = DBAdapter(this)
        var cursor = dbAdapter.allQuery()

        listServicosIos.clear()
        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex("Id"))
                val nome = cursor.getString(cursor.getColumnIndex("nome"))
                val tipo = cursor.getString(cursor.getColumnIndex("tipo"))
                val preco = cursor.getString(cursor.getColumnIndex("preco"))

                listServicosIos.add(Servicos(id, nome, tipo, preco))
            }while (cursor.moveToNext())
        }

        var barangAdapter = BarangAdapter(this, listServicosIos)
        if(lvServicos != null){
           lvServicos.adapter = barangAdapter
       }
    }

    inner class BarangAdapter: BaseAdapter{

        private var iosServicosList = ArrayList<Servicos>()
        private var context: Context? = null

        constructor(context: Context, servicosList: ArrayList<Servicos>) : super(){
            this.iosServicosList = servicosList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val view: View?
            val vh: ViewHolder

            if (convertView == null){
                view = layoutInflater.inflate(R.layout.servicos, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("db", "set tag for ViewHolder, position: " + position)
            }else{
                view = convertView
                vh = view.tag as ViewHolder
            }

            var mBarang = iosServicosList[position]

            vh.tvNome.text = "Serviço: " + mBarang.nome
            vh.tvTipo.text = "Modelo: " + mBarang.tipo
            vh.tvPreco.text = "Preço: R$" + mBarang.preco

            lvServicos.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
              updateBarang(mBarang)
            }

            return view
        }

        override fun getItem(position: Int): Any {
            return iosServicosList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return iosServicosList.size
        }

    }

    private fun updateBarang(servicos: Servicos) {
        var  intent = Intent(this, ServicosActivity::class.java)
        intent.putExtra("MainActId", servicos.id)
        intent.putExtra("MainActNome", servicos.nome)
        intent.putExtra("MainActTipo", servicos.tipo)
        intent.putExtra("MainActPreco", servicos.preco)
        startActivity(intent)
    }

    private class ViewHolder(view: View?){
        val tvNome: TextView
        val tvTipo: TextView
        val tvPreco: TextView

        init {
            this.tvNome = view?.findViewById(R.id.tvNome) as TextView
            this.tvTipo = view?.findViewById(R.id.tvModelo) as TextView
            this.tvPreco = view?.findViewById(R.id.tvPreco) as TextView
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up idLocal, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
