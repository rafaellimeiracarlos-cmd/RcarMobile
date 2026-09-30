package com.example.rcarpremium
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
class MainActivityCadastroProduto:AppCompatActivity(){override fun onCreate(b:Bundle?){super.onCreate(b);setContentView(R.layout.activity_cadastro_produto)
 val nome=findViewById<EditText>(R.id.editNomeProduto);val cat=findViewById<EditText>(R.id.editCategoriaProduto);val preco=findViewById<EditText>(R.id.editPrecoProduto);val estoque=findViewById<EditText>(R.id.editEstoqueProduto)
 findViewById<Button>(R.id.btnSalvarProduto).setOnClickListener{val n=nome.text.toString().trim();val c=cat.text.toString().trim();val p=preco.text.toString().replace(",",".").toDoubleOrNull();val e=estoque.text.toString().toIntOrNull();if(n.isEmpty()||c.isEmpty()||p==null||e==null){Toast.makeText(this,"Preencha todos os campos.",Toast.LENGTH_SHORT).show()}else{AdminLocalStore.addProduct(this,AdminProduto(n,c,p,e));Toast.makeText(this,"Produto cadastrado com sucesso!",Toast.LENGTH_SHORT).show();finish()}}
 findViewById<TextView>(R.id.btnVoltar).setOnClickListener{finish()}}
}
