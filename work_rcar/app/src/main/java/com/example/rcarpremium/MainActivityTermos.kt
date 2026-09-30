package com.example.rcarpremium
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class MainActivityTermos:AppCompatActivity(){override fun onCreate(b:Bundle?){super.onCreate(b);setContentView(R.layout.activity_termos);findViewById<TextView>(R.id.btnVoltar).setOnClickListener{finish()};findViewById<TextView>(R.id.btnAceitar).setOnClickListener{getPreferences(0).edit().putBoolean("accepted",true).apply();finish()}}}
