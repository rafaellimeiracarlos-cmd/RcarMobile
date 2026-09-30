package com.example.rcarpremium
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
class MainActivityRelatorios:AppCompatActivity(){override fun onCreate(b:Bundle?){super.onCreate(b);setContentView(R.layout.activity_relatorios);findViewById<TextView>(R.id.txtVendas).text="R$ %.2f".format(AdminLocalStore.totalSales(this)+1250.90);findViewById<Button>(R.id.btnGerar).setOnClickListener{Toast.makeText(this,"Relatório gerado com dados locais.",Toast.LENGTH_SHORT).show()};findViewById<TextView>(R.id.btnVoltar).setOnClickListener{finish()}}
}
