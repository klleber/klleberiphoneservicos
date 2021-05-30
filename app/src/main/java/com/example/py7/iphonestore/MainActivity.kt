package com.example.py7.iphonestore

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
        //setSupportActionBar(toolbar)

        idServicos.setOnClickListener { view ->
            //val builder = AlertDialog.Builder(this)
            //builder.setTitle("teste");
            //builder.setMessage("tesdsads");
            //builder.show()


            //intent explicita
            var intentLista = Intent(this, ListaServicos::class.java)
            startActivity(intentLista)
        }

        idSobre.setOnClickListener { view ->
            //intent explicita
            var intentSobre = Intent(this, SobreActivity::class.java)
            startActivity(intentSobre)
        }

        idLocal.setOnClickListener { view ->
            //intent explicita
            val mapIntent: Intent = Uri.parse(
                "geo:0,0?q=Recife+Antigo+-+Recife,+PE"
            ).let { location ->
                Intent(Intent.ACTION_VIEW, location)
            }
            startActivity(mapIntent)
        }

    }

}
