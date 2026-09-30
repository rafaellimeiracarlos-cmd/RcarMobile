package com.example.rcarpremium

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

data class AdminProduto(val nome:String, val categoria:String, val preco:Double, var estoque:Int)

object AdminLocalStore {
    private const val PREFS="rcar_admin"
    private const val PRODUCTS="products"
    private const val SALES="sales"
    fun products(context: Context): MutableList<AdminProduto> {
        val raw=context.getSharedPreferences(PREFS,0).getString(PRODUCTS,null)
        if(raw.isNullOrBlank()) return RcarLocalStore.catalog.map { AdminProduto(it.nome,"Automotivo",it.preco,25) }.toMutableList()
        return try { val a=JSONArray(raw); MutableList(a.length()){i-> val o=a.getJSONObject(i); AdminProduto(o.getString("nome"),o.getString("categoria"),o.getDouble("preco"),o.getInt("estoque"))} } catch(_:Exception){ mutableListOf() }
    }
    fun saveProducts(context:Context, list:List<AdminProduto>){ val a=JSONArray(); list.forEach{p->a.put(JSONObject().apply{put("nome",p.nome);put("categoria",p.categoria);put("preco",p.preco);put("estoque",p.estoque)})}; context.getSharedPreferences(PREFS,0).edit().putString(PRODUCTS,a.toString()).apply() }
    fun addProduct(context:Context,p:AdminProduto){ val l=products(context); l.add(p); saveProducts(context,l) }
    fun registerSale(context:Context,total:Double){ val prefs=context.getSharedPreferences(PREFS,0); prefs.edit().putFloat(SALES,prefs.getFloat(SALES,0f)+total.toFloat()).apply() }
    fun totalSales(context:Context)=context.getSharedPreferences(PREFS,0).getFloat(SALES,0f).toDouble()
}
