package com.eduardo.listadecompras

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast


class CadastroActivity : AppCompatActivity() {
    val COD_IMAGE = 101
    var imageBitMap : Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)


        btn_adicionar.setOnClickListener{
            //Criando uma Intent explicita forma antiga sem o import import org.jetbrains.anko.startActivity
            val intent = Intent(this, CadastroActivity::class.java)

            //Iniciando a Intent
            startActivity(intent)

            //Pegando os valores digitado pelos usuários
            val produto = txt_produto.text.toString()
            val qtd = txt_qtd.text.toString()
            val valor = txt_valor.text.toString()

            if (produto.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty()){

                var prod = Produto(0, produto, qtd.toInt(), valor.toDouble())
                produtosGlobal.add(prod)
                //Inserindo dados no banco
                database.use {
                    val idProduto = insert("Produtos",
                        "nome" to produto,
                        "quantidade" to qtd,
                        "valor" to valor.toDouble(),
                        "foto" to imageBitMap?.toByteArray() //Acrescentei a chamada a função de extensão

                    )

                    if(idProduto != -1L){

                        //podemos usa também Toast.makeText(context, "Mensagem", Toast.LENGTH_SHORT).show()
                        toast("ítem inserido com sucesso")

                        txt_produto.text.clear()
                        txt_qtd.text.clear()
                        txt_valor.text.clear()

                    }else{
                        toast("Erro ao inserir no banco de dados")
                    }
                }

            }else{

                //forma simplificada quando for condicional
                txt_produto.error = if (txt_produto.text.isEmpty()) "Preencha o nome do produto" else null

                txt_qtd.error = if (txt_qtd.text.isEmpty()) "Preencha a quantidade" else null

                txt_valor.error = if (txt_valor.text.isEmpty()) "Preencha o valor" else null

            }

        }

        img_foto_produto.setOnClickListener{
            abrirGaleria()



        }
    }
    fun abrirGaleria(){

        val intent = Intent(Intent(Intent.ACTION_GET_CONTENT))
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Selecione uma Imagem"), COD_IMAGE)




    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == COD_IMAGE && resultCode == Activity.RESULT_OK){
            if (data != null){

                //lendo a URI com a imagem
                val inputStream = contentResolver.openInputStream(data.getData()!!);

                //transformando o resultado em bitbmap
                imageBitMap = BitmapFactory.decodeStream(inputStream)

                //Exibit imagem no aplicativo
                img_foto_produto.setImageBitmap(imageBitMap)

            }
        }
    }
}
