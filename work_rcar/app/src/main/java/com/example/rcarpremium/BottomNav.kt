package com.example.rcarpremium

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.TextView

object BottomNav {
    fun setup(activity: Activity, selected: String) {
        val ids = listOf(R.id.navInicio, R.id.navCategorias, R.id.navCarrinho, R.id.navPedidos, R.id.navPerfil)
        val keys = listOf("inicio", "categorias", "carrinho", "pedidos", "perfil")
        ids.forEachIndexed { index, id ->
            activity.findViewById<TextView>(id)?.apply {
                setTextColor(if (keys[index] == selected) Color.rgb(255,122,0) else Color.WHITE)
                setOnClickListener {
                    when (keys[index]) {
                        "inicio" -> activity.startActivity(Intent(activity, MainActivitycomprasonline::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        "categorias" -> activity.startActivity(Intent(activity, MainActivityCategorias::class.java))
                        "carrinho" -> activity.startActivity(Intent(activity, MainActivityItemCarrinho::class.java))
                        "pedidos" -> activity.startActivity(Intent(activity, MainActivityPedidos::class.java))
                        "perfil" -> activity.startActivity(Intent(activity, MainActivityHome::class.java))
                    }
                }
            }
        }
    }
}
