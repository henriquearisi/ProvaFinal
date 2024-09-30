package com.example.provafinal

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*  // Para usar views pelo id automaticamente
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    // Lista de viagens
    private val viagens = mutableListOf<Viagem>()
    private lateinit var viagemAdapter: ViagemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar o Adapter
        viagemAdapter = ViagemAdapter(viagens)

        // Configurar o RecyclerView com o Adapter e o LayoutManager
        recyclerViewViagens.layoutManager = LinearLayoutManager(this)
        recyclerViewViagens.adapter = viagemAdapter

        // Botão para adicionar uma nova viagem
        btnAddViagem.setOnClickListener {
            adicionarViagem()
        }

        // Exibir o total gasto ao iniciar o app
        atualizarTotalGasto()
    }

    private fun adicionarViagem() {
        // Verificar se os campos estão preenchidos
        val destino = editTextDestino.text.toString()
        val dataViagem = editTextDataViagem.text.toString()
        val valorGastoText = editTextValorGasto.text.toString()

        if (destino.isEmpty() || dataViagem.isEmpty() || valorGastoText.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Converter o valor gasto para Float
        val valorGasto = valorGastoText.toFloatOrNull()
        if (valorGasto == null) {
            Toast.makeText(this, "Valor gasto inválido", Toast.LENGTH_SHORT).show()
            return
        }

        // Adicionar a viagem à lista
        val viagem = Viagem(destino, dataViagem, valorGasto)
        viagens.add(viagem)

        // Notificar o Adapter para atualizar a lista
        viagemAdapter.notifyDataSetChanged()

        // Atualizar o total gasto
        atualizarTotalGasto()

        // Limpar os campos
        editTextDestino.text.clear()
        editTextDataViagem.text.clear()
        editTextValorGasto.text.clear()
    }

    private fun atualizarTotalGasto() {
        val totalGasto = viagens.sumOf { it.valorGasto }
        textViewTotalGasto.text = "Total gasto: R$ %.2f".format(totalGasto)
    }
}
