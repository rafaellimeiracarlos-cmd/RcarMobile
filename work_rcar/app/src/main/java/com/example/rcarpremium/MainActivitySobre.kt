package com.example.rcarpremium
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class MainActivitySobre:AppCompatActivity(){override fun onCreate(b:Bundle?){super.onCreate(b);setContentView(R.layout.activity_sobre);findViewById<TextView>(R.id.btnVoltar).setOnClickListener{finish()}}}
