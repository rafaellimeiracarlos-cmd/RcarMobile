package com.example.rcarpremium

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

object RcarLocalStore {
    private const val PREFS = "rcarpremium_app"
    private const val CART = "cart"
    private const val EMAIL = "session_email"

    fun login(context: Context, email: String) = prefs(context).edit().putString(EMAIL, email.trim().lowercase()).apply()
    fun logout(context: Context) = prefs(context).edit().remove(EMAIL).apply()
    fun currentEmail(context: Context): String? = prefs(context).getString(EMAIL, null)

    fun cart(context: Context): MutableList<ProdutoItem> {
        val raw = prefs(context).getString(CART, null) ?: return mutableListOf()
        return try {
            val arr = JSONArray(raw)
            MutableList(arr.length()) { i ->
                val o = arr.getJSONObject(i)
                ProdutoItem(o.getString("nome"), o.getDouble("preco"), o.getInt("qtd"), R.drawable.pecasautomotiva)
            }
        } catch (_: Exception) { mutableListOf() }
    }

    fun saveCart(context: Context, items: List<ProdutoItem>) {
        val arr = JSONArray()
        items.forEach { p -> arr.put(JSONObject().apply { put("nome", p.nome); put("preco", p.preco); put("qtd", p.quantidade) }) }
        prefs(context).edit().putString(CART, arr.toString()).apply()
    }

    fun addToCart(context: Context, product: ProdutoItem) {
        val items = cart(context)
        val existing = items.firstOrNull { it.nome == product.nome }
        if (existing != null) existing.quantidade += product.quantidade else items.add(product.copy())
        saveCart(context, items)
    }

    fun clearCart(context: Context) = prefs(context).edit().remove(CART).apply()
    fun total(context: Context) = cart(context).sumOf { it.preco * it.quantidade }
    fun count(context: Context) = cart(context).sumOf { it.quantidade }

    val catalog = listOf(
        ProdutoItem("Pastilha de Freio RCar", 150.00, 1, R.drawable.pecasautomotiva),
        ProdutoItem("Óleo Sintético 5W30", 45.90, 1, R.drawable.pecasautomotiva),
        ProdutoItem("Amortecedor Dianteiro Premium", 159.90, 1, R.drawable.pecasautomotiva),
        ProdutoItem("Kit Suspensão RCar", 349.90, 1, R.drawable.pecasautomotiva),
        ProdutoItem("Filtro de Óleo Premium", 39.90, 1, R.drawable.pecasautomotiva)
    )

    private fun prefs(context: Context) = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
}
