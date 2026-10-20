package com.example.rcarpremium

import android.content.Context

/**
 * Armazenamento local de usuários.
 * Não depende de Retrofit ou de uma API externa.
 */
class UsuarioLocalRepository(context: Context) {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    init {
        // Garante que o CEO já esteja cadastrado por padrão no primeiro uso
        cadastrarAdminInicial()
    }

    private fun cadastrarAdminInicial() {
        val emailCeo = "Rafael@rcarpremium.com".trim().lowercase()
        if (!prefs.contains(keySenha(emailCeo))) {
            prefs.edit()
                .putString(keyNome(emailCeo), "Rafael (CEO)")
                .putString(keyEmail(emailCeo), emailCeo)
                .putString(keySenha(emailCeo), "006365Rafa@")
                .apply()
        }
    }

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

    // Identifica se o usuário logado é o CEO com base no e-mail
    fun isCeo(email: String): Boolean {
        return email.trim().lowercase() == "Rafael@rcarpremium.com".lowercase()
    }

    private fun keyNome(email: String) = "usuario_nome_$email"
    private fun keyEmail(email: String) = "usuario_email_$email"
    private fun keySenha(email: String) = "usuario_senha_$email"

    companion object {
        private const val PREFS_NAME = "rcarpremium_usuarios"
    }
}