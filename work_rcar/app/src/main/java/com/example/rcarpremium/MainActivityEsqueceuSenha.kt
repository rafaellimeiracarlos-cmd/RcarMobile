package com.example.rcarpremium

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivityEsqueceuSenha : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_esqueceu_senha)

        // Configuração segura de margens da tela
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView.rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. MAPEIA OS CAMPOS DO XML DE RECUPERAR SENHA
        val editEmailRecuperar = findViewById<EditText>(R.id.editEmailRecuperar)
        val btnEnviar = findViewById<Button>(R.id.btnEnviarLink)
        val textVoltarRecuperar = findViewById<TextView>(R.id.textVoltarLoginRecuperar)

        // 2. LÓGICA DO BOTÃO "ENVIAR LINK"
        btnEnviar.setOnClickListener {
            val email = editEmailRecuperar.text.toString().trim()

            if (email.isEmpty()) {
                editEmailRecuperar.error = "Por favor, digite seu e-mail"
                editEmailRecuperar.requestFocus()
            } else {
                val existe = UsuarioLocalRepository(this).existeUsuario(email) || email.equals("teste@rcar.com", true)
                if (existe) {
                    Toast.makeText(this, "Solicitação registrada para $email. Como o app está offline, a redefinição deve ser feita localmente.", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    editEmailRecuperar.error = "E-mail não encontrado"
                }
            }
        }

        // 3. AÇÃO DO TEXTO "VOLTAR PARA O LOGIN"
        textVoltarRecuperar.setOnClickListener {
            finish() // Fecha a tela atual e retorna
        }
    }
}


