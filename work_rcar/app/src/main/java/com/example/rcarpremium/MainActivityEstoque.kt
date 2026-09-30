package com.example.rcarpremium
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
class MainActivityEstoque:AppCompatActivity(){override fun onCreate(b:Bundle?){super.onCreate(b);setContentView(R.layout.activity_estoque);val c=findViewById<LinearLayout>(R.id.listaEstoque);AdminLocalStore.products(this).forEach{p->val t=TextView(this);t.text="${p.nome}\n${p.categoria} • ${p.estoque} unidades";t.setTextColor(android.graphics.Color.WHITE);t.textSize=14f;t.setPadding(18,18,18,18);t.setBackgroundColor(android.graphics.Color.rgb(28,28,28));c.addView(t,ViewGroup.LayoutParams(-1,-2));val lp=t.layoutParams as LinearLayout.LayoutParams;lp.bottomMargin=8;t.layoutParams=lp};findViewById<TextView>(R.id.btnVoltar).setOnClickListener{finish()}}
}
