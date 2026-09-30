package com.example.rcarpremium

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivityVerProdutos : AppCompatActivity() {

    private var quantidade: Int = 1
    private val precoUnitario: Double = 150.00

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_ver_produtos)

        // 1. Mapeamento dos Componentes
        val btnVoltarinicial = findViewById<ImageView>(R.id.btnVoltarinicial)
        val btnCarrinhoTopo = findViewById<ImageView>(R.id.btnCarrinhoTopo)
        val txtPreco = findViewById<TextView>(R.id.txtPrecoProdutoDetalhe)
        val txtParcelamento = findViewById<TextView>(R.id.txtParcelamento)
        val txtQuantidade = findViewById<TextView>(R.id.txtQuantidadeDetalhe)
        val btnDiminuir = findViewById<Button>(R.id.btnDiminuirQtd)
        val btnAumentar = findViewById<Button>(R.id.btnAumentarQtd)
        val btnComprarAgora = findViewById<Button>(R.id.btnComprarAgora)
        val btnAdicionarAoCarrinho = findViewById<Button>(R.id.btnAdicionarAoCarrinho)
        val txtNomeProduto = findViewById<TextView>(R.id.txtNomeProdutoDetalhe)
        val nomeRecebido = intent.getStringExtra("PRODUTO_NOME")
        val precoRecebido = intent.getDoubleExtra("PRODUTO_PRECO", 150.00)
        val precoAtual = if (precoRecebido > 0) precoRecebido else 150.00
        txtNomeProduto.text = nomeRecebido ?: "Kit Pastilha de Freio Dianteira RCar Premium Alta Performance"

        // Locale do Brasil para formatar no padrão de moeda R$
        val ptBr = Locale("pt", "BR")

        // 2. Função para atualizar os valores na tela
        fun atualizarValores() {
            val total = precoAtual * quantidade
            val valorParcela = total / 3
            txtQuantidade.text = quantidade.toString()
            txtPreco.text = String.format(ptBr, "R$ %.2f", total)
            txtParcelamento.text = String.format(ptBr, "em 3x de R$ %.2f sem juros", valorParcela)
        }

        // 3. Controle dos Botões de Quantidade
        btnAumentar.setOnClickListener(fun(it: View) {
            quantidade++
            atualizarValores()
        })

        btnDiminuir.setOnClickListener {
            if (quantidade > 1) {
                quantidade--
                atualizarValores()
            }
        }
        atualizarValores()

        // 4. Ações dos Botões Principais
        btnVoltarinicial.setOnClickListener {
            finish()
        }

        btnCarrinhoTopo.setOnClickListener {
            startActivity(Intent(this, MainActivityItemCarrinho::class.java))
        }

        btnAdicionarAoCarrinho.setOnClickListener {
            RcarLocalStore.addToCart(this, ProdutoItem(txtNomeProduto.text.toString(), precoAtual, quantidade, R.drawable.pecasautomotiva))
            Toast.makeText(this, "Produto adicionado ao carrinho!", Toast.LENGTH_SHORT).show()
        }

        // 5. Botão Comprar Agora (Calcula e envia o total correto)
        btnComprarAgora.setOnClickListener {
            val totalCalculado = precoAtual * this.quantidade
            val totalFormatado = String.format(ptBr, "R$ %.2f", totalCalculado)

            RcarLocalStore.addToCart(this, ProdutoItem(txtNomeProduto.text.toString(), precoAtual, quantidade, R.drawable.pecasautomotiva))
            val intent = Intent(this, MainActivityPagamento::class.java)
            intent.putExtra("TOTAL_PEDIDO", totalFormatado)
            startActivity(intent)
        }
    }
}