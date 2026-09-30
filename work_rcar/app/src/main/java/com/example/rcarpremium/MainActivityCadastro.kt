package com.example.rcarpremium

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivityCadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_cadastro)

        // Configuração de margens para a barra do sistema
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView.rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. MAPEIA OS CAMPOS E ELEMENTOS DO XML
        val editNome = findViewById<EditText>(R.id.editNomeCadastro)
        val editEmail = findViewById<EditText>(R.id.editEmailCadastro)
        val editSenha = findViewById<EditText>(R.id.editSenhaCadastro)
        val btnFinalizar = findViewById<Button>(R.id.btnFinalizarCadastro)
        val textVoltar = findViewById<TextView>(R.id.textVoltarLogin)
        val imgLogo = findViewById<ImageView>(R.id.img_logo)

        // 2. LÓGICA DE CADASTRO
        btnFinalizar.setOnClickListener {
            val nome = editNome.text.toString().trim()
            val email = editEmail.text.toString().trim()
            val senha = editSenha.text.toString().trim()

            if (nome.isEmpty()) {
                editNome.error = "Digite seu nome completo"
                editNome.requestFocus()
            } else if (email.isEmpty()) {
                editEmail.error = "Digite seu e-mail"
                editEmail.requestFocus()
            } else if (senha.isEmpty()) {
                editSenha.error = "Crie uma senha"
                editSenha.requestFocus()
            } else if (senha.length < 4) {
                editSenha.error = "A senha precisa ter pelo menos 4 dígitos"
                editSenha.requestFocus()
            } else {
                val repositorio = UsuarioLocalRepository(this)
                val cadastrado = repositorio.cadastrar(nome, email, senha)

                if (cadastrado) {
                    Toast.makeText(
                        this,
                        "Cadastro de $nome realizado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Este e-mail já está cadastrado.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        // 3. NAVEGAÇÃO
        textVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        imgLogo.setOnClickListener {
            finish()
        }
    }
}