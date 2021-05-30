package com.example.py7.iphonestore

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_activity.idSobre
import kotlinx.android.synthetic.main.sobre_activity.*


class SobreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.sobre_activity)

        ligarKleber.setOnClickListener { view ->
            //intent implicita
            val callIntent: Intent = Uri.parse("tel:81996183307").let { number ->
                Intent(Intent.ACTION_DIAL, number)
            }
            startActivity(callIntent)
        }

        idSite.setOnClickListener { view ->
            //intent implicita
            val webIntent: Intent = Uri.parse("https://www.android.com").let { webpage ->
               Intent(Intent.ACTION_VIEW, webpage)
            }
            startActivity(webIntent)
        }
    }

}
