package com.example.rcarpremium

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivityAcessoInterno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acesso_interno)

        val papel = intent.getStringExtra("PAPEL") ?: "FUNCIONARIO"
        val titulo = findViewById<TextView>(R.id.txtTituloAcesso)
        val subtitulo = findViewById<TextView>(R.id.txtSubtituloAcesso)
        val usuario = findViewById<EditText>(R.id.editUsuarioInterno)
        val senha = findViewById<EditText>(R.id.editSenhaInterno)
        val btnEntrar = findViewById<Button>(R.id.btnEntrarInterno)
        val btnVoltar = findViewById<ImageView>(R.id.btnVoltarInterno)
        val logo = findViewById<ImageView>(R.id.imgLogoInterno)

        val isCeo = papel == "CEO"
        titulo.text = if (isCeo) "Login do CEO" else "Login do Funcionário"
        subtitulo.text = if (isCeo) "Acesso total ao sistema" else "Acesso ao sistema interno"

        btnEntrar.setOnClickListener {
            val u = usuario.text.toString().trim()
            val s = senha.text.toString().trim()
            val usuarioEsperado = if (isCeo) "ceo@gmail.com" else "funcionario@gmail.com"
            val senhaEsperada = "1234"

            if (u.isEmpty()) {
                usuario.error = "Digite o usuário"
                usuario.requestFocus()
            } else if (s.isEmpty()) {
                senha.error = "Digite a senha"
                senha.requestFocus()
            } else if (u.equals(usuarioEsperado, true) && s == senhaEsperada) {
                startActivity(Intent(this, MainActivityPainelInterno::class.java).apply {
                    putExtra("PAPEL", papel)
                })
                finish()
            } else {
                Toast.makeText(this, "Usuário ou senha incorretos.", Toast.LENGTH_SHORT).show()
            }
        }

        btnVoltar.setOnClickListener { finish() }
        logo.setOnClickListener { finish() }
    }
}
