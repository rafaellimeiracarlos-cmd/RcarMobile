package com.example.rcarpremium

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivityPedidos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)
        findViewById<TextView>(R.id.btnVoltarPedidos).setOnClickListener { finish() }
        BottomNav.setup(this, "pedidos")
    }
}
