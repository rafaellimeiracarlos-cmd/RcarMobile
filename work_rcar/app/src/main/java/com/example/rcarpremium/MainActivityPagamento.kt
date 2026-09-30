package com.example.rcarpremium

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivityPagamento : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_pagamento)

        // 1. Mapeamento dos Componentes
        val btnVoltar = findViewById<ImageView>(R.id.btnVoltar)
        val rgFormasPagamento = findViewById<RadioGroup>(R.id.rgFormasPagamento)
        val rbCartao = findViewById<RadioButton>(R.id.rbCartao)
        val layoutDadosCartao = findViewById<LinearLayout>(R.id.layoutDadosCartao)
        val btnConfirmarPagamento = findViewById<Button>(R.id.btnConfirmarPagamento)

        val txtTotalPagar = findViewById<TextView>(R.id.txtTotalPagar)
        val txtPrecoProdutos = findViewById<TextView>(R.id.txtPrecoProdutos)
        val txtProdutosQtd = findViewById<TextView>(R.id.txtProdutosQtd)

        // 2. Recebe os dados enviados via Intent
        val valorDouble = intent.getDoubleExtra("TOTAL_PEDIDO", -1.0)
        val valorString = intent.getStringExtra("TOTAL_PEDIDO")
        val qtdProdutos = intent.getIntExtra("QTD_PRODUTOS", 0)

        // Trata a formatação do valor total usando a extensão nativa de String do Kotlin
        val valorFormatado = when {
            valorDouble != -1.0 -> "R$ %.2f".format(valorDouble)
            !valorString.isNullOrEmpty() -> valorString
            else -> "R$ 0,00"
        }

        android.util.Log.d("PAGAMENTO_DEBUG", "Valor final processado: $valorFormatado | Qtd: $qtdProdutos")

        // Atualiza as TextViews com os valores do carrinho
        txtTotalPagar?.text = valorFormatado
        txtPrecoProdutos?.text = valorFormatado
        txtProdutosQtd?.text = "Produtos ($qtdProdutos)"

        // 3. Ação do Botão Voltar
        btnVoltar?.setOnClickListener {
            finish()
        }

        // 4. Exibir/Ocultar formulário de Cartão
        rgFormasPagamento?.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rbCartao) {
                layoutDadosCartao?.visibility = View.VISIBLE
            } else {
                layoutDadosCartao?.visibility = View.GONE
            }
        }

        // 5. Confirmar Pagamento
        btnConfirmarPagamento?.setOnClickListener {
            val opcaoSelecionada = when (rgFormasPagamento?.checkedRadioButtonId) {
                R.id.rbPix -> "Pix"
                R.id.rbCartao -> "Cartão de Crédito"
                R.id.rbBoleto -> "Boleto"
                else -> "Desconhecida"
            }

            if (opcaoSelecionada == "Desconhecida") {
                Toast.makeText(this, "Selecione uma forma de pagamento.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (opcaoSelecionada == "Cartão de Crédito") {
                val numero = findViewById<android.widget.EditText>(R.id.editNumeroCartao).text.toString().filter { it.isDigit() }
                val validade = findViewById<android.widget.EditText>(R.id.editValidade).text.toString().trim()
                val cvc = findViewById<android.widget.EditText>(R.id.editCVC).text.toString().trim()
                val titular = findViewById<android.widget.EditText>(R.id.editNomeTitular).text.toString().trim()
                if (numero.length < 12 || validade.isEmpty() || cvc.length < 3 || titular.isEmpty()) {
                    Toast.makeText(this, "Confira os dados do cartão.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            val totalVenda = RcarLocalStore.total(this)
            AdminLocalStore.registerSale(this, totalVenda)
            RcarLocalStore.clearCart(this)
            Toast.makeText(this, "Pedido confirmado! Pagamento via $opcaoSelecionada.", Toast.LENGTH_LONG).show()
            btnConfirmarPagamento?.postDelayed({ finish() }, 1200)
        }
    }
}