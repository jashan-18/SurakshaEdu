package com.example.surakshaedu.materials

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.surakshaedu.core.model.Material
import com.example.surakshaedu.databinding.ItemMaterialCardBinding

class MaterialAdapter(
    private val list: List<Material>
) : RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder>() {

    inner class MaterialViewHolder(val binding: ItemMaterialCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        val binding = ItemMaterialCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MaterialViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
        val material = list[position]
        holder.binding.tvTitle.text = material.title
        holder.binding.tvDesc.text = material.description
    }

    override fun getItemCount() = list.size
}
