package com.provaFinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_viagem.view.*

class ViagemAdapter(private val viagens: List<Viagem>) : RecyclerView.Adapter<ViagemAdapter.ViagemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViagemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_viagem, parent, false)
        return ViagemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViagemViewHolder, position: Int) {
        val viagem = viagens[position]
        holder.bind(viagem)
    }

    override fun getItemCount() = viagens.size

    class ViagemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(viagem: Viagem) {
            itemView.textViewDestino.text = viagem.destino
            itemView.textViewDataViagem.text = viagem.dataViagem
            itemView.textViewValorGasto.text = "R$ %.2f".format(viagem.valorGasto)
        }
    }
}
