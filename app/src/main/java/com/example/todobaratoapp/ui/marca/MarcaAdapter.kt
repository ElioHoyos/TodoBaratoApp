package com.example.todobaratoapp.ui.marca

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todobaratoapp.R
import com.example.todobaratoapp.data.model.Marca
import com.example.todobaratoapp.databinding.ItemMarcaBinding

class MarcaAdapter : ListAdapter<Marca, MarcaAdapter.MarcaViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarcaViewHolder {
        val binding = ItemMarcaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarcaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarcaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MarcaViewHolder(private val binding: ItemMarcaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(marca: Marca) {
            binding.textNombre.text = marca.nombre
            if (marca.estado) {
                binding.textEstado.text = binding.root.context.getString(R.string.estado_activa)
                binding.textEstado.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.active_green)
                )
            } else {
                binding.textEstado.text = binding.root.context.getString(R.string.estado_inactiva)
                binding.textEstado.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.error_red)
                )
            }
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<Marca>() {
        override fun areItemsTheSame(oldItem: Marca, newItem: Marca) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Marca, newItem: Marca) = oldItem == newItem
    }
}