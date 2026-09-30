package com.example.rcarpremium

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class MainActivityPainelInterno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_painel_interno)

        val papel = intent.getStringExtra("PAPEL") ?: "FUNCIONARIO"
        findViewById<TextView>(R.id.txtTituloPainel).text = if (papel == "CEO") "Painel do CEO" else "Painel do Funcionário"
        findViewById<TextView>(R.id.txtDescricaoPainel).text = if (papel == "CEO") {
            "Acesso administrativo completo"
        } else {
            "Operações de vendas e cadastro de produtos"
        }

        findViewById<Button>(R.id.btnProdutosInterno).setOnClickListener {
            startActivity(Intent(this, MainActivityListaProdutos::class.java))
        }
        findViewById<Button>(R.id.btnCadastrarProduto).setOnClickListener { startActivity(Intent(this, MainActivityCadastroProduto::class.java)) }
        findViewById<Button>(R.id.btnClientesInterno).setOnClickListener { startActivity(Intent(this, MainActivityClientes::class.java)) }
        findViewById<Button>(R.id.btnConfiguracoesInterno).setOnClickListener { startActivity(Intent(this, MainActivityConfiguracoes::class.java)) }

        findViewById<Button>(R.id.btnVendasInterno).setOnClickListener {
            startActivity(Intent(this, MainActivityVendas::class.java))
        }
        findViewById<Button>(R.id.btnEstoqueInterno).setOnClickListener {
            startActivity(Intent(this, MainActivityEstoque::class.java))
        }
        findViewById<Button>(R.id.btnRelatoriosInterno).setOnClickListener {
            startActivity(Intent(this, MainActivityRelatorios::class.java))
        }
        findViewById<Button>(R.id.btnSairInterno).setOnClickListener { finish() }
    }
}
