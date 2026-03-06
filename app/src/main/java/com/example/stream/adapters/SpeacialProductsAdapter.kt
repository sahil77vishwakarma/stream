package com.example.stream.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.stream.data.Product
import com.example.stream.databinding.SpecialRvItemBinding

class SpeacialProductsAdapter : RecyclerView.Adapter<SpeacialProductsAdapter.SpeacialProductsViewHolder>() {

    inner class SpeacialProductsViewHolder(private val binding: SpecialRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }

    }


    val differ = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpeacialProductsViewHolder {
            return SpeacialProductsViewHolder(
                SpecialRvItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    override fun onBindViewHolder(
        holder: SpeacialProductsViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}