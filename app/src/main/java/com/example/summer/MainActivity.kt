package com.example.summer


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var etNome: EditText
    private lateinit var etPreco: EditText
    private lateinit var listView: ListView
    private lateinit var dao: ItemDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.dao = ItemDAO(this)

        this.button = findViewById(R.id.button)
        this.etNome = findViewById(R.id.etNome)
        this.etPreco = findViewById(R.id.etPreco)
        this.listView = findViewById(R.id.listView)

        this.atualiza()

        this.listView.setOnItemClickListener(OnItemClick())
        this.listView.setOnItemLongClickListener(OnItemLongClick())

        this.button.setOnClickListener { salvar() }
        this.button.setOnClickListener { somar(this.etPreco) }
    }

    fun salvar(){
        val nome = this.etNome.text.toString()
        val preco = this.etPreco.text.toString()
        var precoFloat = preco.toFloat()
        val item = Item(nome, precoFloat)
        this.dao.insert(item)
        this.atualiza()
        this.etNome.setText("")
    }
    fun somar(preco: EditText): Float {
        var total: Float = 1F
        total = total + preco.toString().toFloat()
        return total
    }

    fun atualiza(){
        val layout = android.R.layout.simple_list_item_1
        this.listView.adapter = ArrayAdapter<Item>(this, layout,this.dao.read())
    }

    inner class OnItemClick: AdapterView.OnItemClickListener{
        override fun onItemClick(adapter: AdapterView<*>?, view: View?, index: Int, id: Long) {
            val pessoa = adapter?.getItemAtPosition(index) as Item
            Toast.makeText(this@MainActivity, pessoa?.nome, Toast.LENGTH_SHORT).show()
        }
    }

    inner class OnItemLongClick: AdapterView.OnItemLongClickListener{
        override fun onItemLongClick(adapter: AdapterView<*>?, view: View?, index: Int, id: Long): Boolean {
            val pessoa = adapter?.getItemAtPosition(index) as Item
            this@MainActivity.dao.delete(pessoa)
            val msg = "${pessoa.nome} removido com sucesso!"
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            this@MainActivity.atualiza()
            return true
        }
    }
}