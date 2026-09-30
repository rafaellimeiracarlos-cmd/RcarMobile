package com.example.rcarpremium
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
class MainActivityClientes:AppCompatActivity(){override fun onCreate(b:Bundle?){super.onCreate(b);setContentView(R.layout.activity_clientes);val c=findViewById<LinearLayout>(R.id.listaClientes);val dados=listOf("Rafael Carlos" to "rafael@email.com","Maria Silva" to "maria@email.com","João Santos" to "joao@email.com","Ana Costa" to "ana@email.com");dados.forEach{(n,e)->val t=TextView(this);t.text="👤  $n\n     $e";t.setTextColor(android.graphics.Color.WHITE);t.textSize=15f;t.setPadding(20,20,20,20);t.setBackgroundColor(android.graphics.Color.rgb(28,28,28));c.addView(t,ViewGroup.LayoutParams(-1,-2));val sp=t.layoutParams as LinearLayout.LayoutParams;sp.bottomMargin=8;t.layoutParams=sp};findViewById<TextView>(R.id.btnVoltar).setOnClickListener{finish()}}
}
