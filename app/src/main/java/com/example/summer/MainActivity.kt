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
        this.button.setOnClickListener { somar(this.etPreco.text.toString()) }
    }

    fun salvar(){
        val nome = this.etNome.text.toString()
        val preco = this.etPreco.text.toString()
        var precoDouble = preco.toDouble()
        val item = Item(nome, precoDouble)
        this.dao.insert(item)
        this.atualiza()
        this.etNome.setText("")
    }
    fun somar(preco: String): Double {
        var total = 0.0
        total += preco.toDouble()
        return total
    }

    fun atualiza(){
        val layout = android.R.layout.simple_list_item_1
        this.listView.adapter = ArrayAdapter(this, layout, this.dao.read())
    }

    inner class OnItemClick: AdapterView.OnItemClickListener{
        override fun onItemClick(adapter: AdapterView<*>?, view: View?, index: Int, id: Long) {
            val pessoa = adapter?.getItemAtPosition(index) as Item
            Toast.makeText(this@MainActivity, pessoa?.nome, Toast.LENGTH_SHORT).show()
        }
    }

    inner class OnItemLongClick: AdapterView.OnItemLongClickListener{
        override fun onItemLongClick(adapter: AdapterView<*>?, view: View?, index: Int, id: Long): Boolean {
            val item = adapter?.getItemAtPosition(index) as Item
            this@MainActivity.dao.delete(item)
            val msg = "${item.nome} removido com sucesso!"
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            this@MainActivity.atualiza()
            return true
        }
    }
}
