package com.example.rcarpremium
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
class MainActivityVendas:AppCompatActivity(){override fun onCreate(b:Bundle?){super.onCreate(b);setContentView(R.layout.activity_vendas);findViewById<TextView>(R.id.txtTotalVendas).text="R$ %.2f".format(AdminLocalStore.totalSales(this)+1250.90);findViewById<TextView>(R.id.btnVoltar).setOnClickListener{finish()}}
}
