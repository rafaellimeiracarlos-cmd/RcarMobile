package com.example.rcarpremium

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivitycomprasonline : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activitycomprasonline)

        BottomNav.setup(this, "inicio")

        val btnIrCadastro = findViewById<View>(R.id.btnIrCadastro)
        val btnIrLogin = findViewById<View>(R.id.btnIrLogin)
        val editPesquisa = findViewById<EditText>(R.id.editPesquisa)
        val btnLupaPesquisa = findViewById<ImageButton>(R.id.btnLupaPesquisa)
        val btnComprar = findViewById<View>(R.id.btncomprar)
        val btnVerProdutos = findViewById<View>(R.id.btnVerprodutos)
        val btnMenuCategorias = findViewById<ImageButton>(R.id.btnMenuCategorias)
        val btnAcessoInterno = findViewById<View>(R.id.btnAcessoInterno)

        btnIrCadastro.setOnClickListener {
            startActivity(Intent(this, MainActivityCadastro::class.java))
        }

        btnIrLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnMenuCategorias.setOnClickListener {
            startActivity(Intent(this, MainActivityCategorias::class.java))
        }

        btnAcessoInterno.setOnClickListener {
            val opcoes = arrayOf("Login do Funcionário", "Login do CEO")
            AlertDialog.Builder(this)
                .setTitle("Acesso interno RCAR Premium")
                .setItems(opcoes) { _, which ->
                    val papel = if (which == 1) "CEO" else "FUNCIONARIO"
                    startActivity(Intent(this, MainActivityAcessoInterno::class.java).apply {
                        putExtra("PAPEL", papel)
                    })
                }
                .show()
        }

        btnLupaPesquisa.setOnClickListener { executarBusca(editPesquisa) }
        editPesquisa.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH) {
                executarBusca(editPesquisa)
                true
            } else false
        }

        btnComprar.setOnClickListener {
            startActivity(Intent(this, MainActivityItemCarrinho::class.java))
        }

        btnVerProdutos.setOnClickListener {
            startActivity(Intent(this, MainActivityVerProdutos::class.java))
        }

        findViewById<View>(R.id.cardFreios).setOnClickListener { abrirCategoria("Freios") }
        findViewById<View>(R.id.cardSuspensao).setOnClickListener { abrirCategoria("Suspensão") }
        findViewById<View>(R.id.cardOleo).setOnClickListener { abrirCategoria("Óleos e lubrificantes") }
        findViewById<View>(R.id.cardPneus).setOnClickListener { abrirCategoria("Pneus e rodas") }
        findViewById<View>(R.id.cardFerramentas).setOnClickListener { abrirCategoria("Ferramentas") }
    }

    private fun abrirCategoria(nome: String) {
        startActivity(Intent(this, MainActivityListaProdutos::class.java).apply {
            putExtra("CATEGORIA", nome)
        })
    }

    private fun executarBusca(editPesquisa: EditText) {
        val texto = editPesquisa.text.toString().trim()
        if (texto.isEmpty()) {
            editPesquisa.error = "Digite o nome de um produto"
            editPesquisa.requestFocus()
        } else {
            Toast.makeText(this, "Buscando por: $texto", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivityVerProdutos::class.java).apply {
                putExtra("PRODUTO_NOME", texto)
                putExtra("PRODUTO_PRECO", 49.90)
            })
        }
    }
}
