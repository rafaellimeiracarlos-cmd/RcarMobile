package com.example.rcarpremium

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivityListaProdutos: AppCompatActivity(){
 override fun onCreate(savedInstanceState:Bundle?){super.onCreate(savedInstanceState);setContentView(R.layout.activity_lista_produtos);BottomNav.setup(this,"categorias")
  val categoria=intent.getStringExtra("CATEGORIA")
  findViewById<TextView>(R.id.txtTituloLista).text=categoria ?: "Todos os produtos"
  findViewById<TextView>(R.id.btnVoltarLista).setOnClickListener{finish()}
  val container=findViewById<LinearLayout>(R.id.listaProdutos)
  val lista=AdminLocalStore.products(this).filter{categoria.isNullOrBlank() || categoria=="Todos" || it.categoria.equals(categoria,true) || categoria.lowercase().contains(it.categoria.lowercase()) || it.nome.lowercase().contains(categoria.lowercase())}
  (if(lista.isEmpty()) AdminLocalStore.products(this) else lista).forEach{p->
   val card=layoutInflater.inflate(R.layout.item_produto_catalogo,container,false)
   card.findViewById<TextView>(R.id.itemNome).text=p.nome
   card.findViewById<TextView>(R.id.itemCategoria).text="${p.categoria} • ${p.estoque} em estoque"
   card.findViewById<TextView>(R.id.itemPreco).text="R$ %.2f".format(p.preco)
   card.setOnClickListener{startActivity(Intent(this,MainActivityVerProdutos::class.java).apply{putExtra("PRODUTO_NOME",p.nome);putExtra("PRODUTO_PRECO",p.preco)})}
   container.addView(card,ViewGroup.LayoutParams(-1,-2))
  }
 }
}
