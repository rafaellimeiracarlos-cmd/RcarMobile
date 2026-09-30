package com.example.rcarpremium

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivityCategorias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)
        findViewById<TextView>(R.id.btnVoltarCategorias).setOnClickListener { finish() }
        val categorias = listOf(
            "Shampoo Automotivo", "Cera e Cristalização", "Higienização e Limpeza",
            "Óleos e Lubrificantes", "Pneus e Rodas", "Freios", "Suspensão",
            "Elétrica", "Ferramentas", "Acessórios"
        )
        val container = findViewById<android.widget.LinearLayout>(R.id.listaCategorias)
        categorias.forEach { nome ->
            val item = TextView(this).apply {
                text = "▣   $nome   ›"
                textSize = 15f
                setTextColor(android.graphics.Color.WHITE)
                setPadding(20, 22, 16, 22)
                setBackgroundColor(android.graphics.Color.rgb(28,28,28))
                setOnClickListener {
                    startActivity(Intent(this@MainActivityCategorias, MainActivityListaProdutos::class.java).apply {
                        putExtra("CATEGORIA", nome)
                    })
                }
            }
            container.addView(item, android.widget.LinearLayout.LayoutParams(-1, -2).apply { bottomMargin = 8 })
        }
        BottomNav.setup(this, "categorias")
    }
}
