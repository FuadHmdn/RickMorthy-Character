package com.rickmorthy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rickmorthy.databinding.ItemCharacterBinding

class ListCharacterAdapter(private val listItem: List<Character>) :
    RecyclerView.Adapter<ListCharacterAdapter.ListViewHolder>() {

    lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val listItemBinding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(listItemBinding)
    }

    override fun getItemCount(): Int {
        return listItem.size - 1
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.name.text = listItem[position].name
        holder.binding.status.text = listItem[position].status
        holder.binding.image.setImageResource(listItem[position].photo)
        holder.binding.root.setOnClickListener { (onItemClickCallback.onClickedItem(listItem[position])) }
    }

    interface OnItemClickCallback {
        fun onClickedItem(data: Character)
    }
}