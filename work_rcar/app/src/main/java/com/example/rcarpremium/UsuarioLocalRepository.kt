package com.example.rcarpremium

import android.content.Context

/**
 * Armazenamento local de usuários.
 * Não depende de Retrofit, Visual Studio ou de uma API externa.
 */
class UsuarioLocalRepository(context: Context) {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun cadastrar(nome: String, email: String, senha: String): Boolean {
        val emailNormalizado = email.trim().lowercase()

        if (emailNormalizado.isEmpty() || prefs.contains(keySenha(emailNormalizado))) {
            return false
        }

        prefs.edit()
            .putString(keyNome(emailNormalizado), nome.trim())
            .putString(keyEmail(emailNormalizado), emailNormalizado)
            .putString(keySenha(emailNormalizado), senha)
            .apply()

        return true
    }

    fun autenticar(email: String, senha: String): Boolean {
        val emailNormalizado = email.trim().lowercase()
        val senhaSalva = prefs.getString(keySenha(emailNormalizado), null)
        return senhaSalva == senha
    }

    fun existeUsuario(email: String): Boolean {
        return prefs.contains(keySenha(email.trim().lowercase()))
    }

    fun nomeDoUsuario(email: String): String? {
        return prefs.getString(keyNome(email.trim().lowercase()), null)
    }

    private fun keyNome(email: String) = "usuario_nome_$email"
    private fun keyEmail(email: String) = "usuario_email_$email"
    private fun keySenha(email: String) = "usuario_senha_$email"

    companion object {
        private const val PREFS_NAME = "rcarpremium_usuarios"
    }
}
