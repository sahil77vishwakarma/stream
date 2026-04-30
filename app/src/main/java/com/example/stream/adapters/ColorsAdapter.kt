package com.example.stream.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.stream.databinding.ColorRvItemBinding

class ColorsAdapter : RecyclerView.Adapter<ColorsAdapter.ColorsViewHolder>() {

    private val selectedPosition = -1
    inner class ColorsViewHolder(private val binding: ColorRvItemBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(color: Int, position: Int){
                if(position == selectedPosition) {
                    binding.imageShadow.visibility = View.VISIBLE
                    binding.imagePicked.visibility = View.VISIBLE
                }else{
                    binding.imageShadow.visibility = View.INVISIBLE
                    binding.imagePicked.visibility = View.INVISIBLE
                    }
            }
        }

    private val diffCallback = object : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }


    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ColorsViewHolder {
        return ColorsViewHolder(
            ColorRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(
        holder: ColorsViewHolder,
        position: Int
    ) {
        val color = differ.currentList[position]
        holder.bind(color, position)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}