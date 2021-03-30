package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Result

class CharacterAdapter(
        private val resultist: List<Result>,
        private val listener: CharacterViewHolder.Listener
) : RecyclerView.Adapter<CharacterViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_character, parent, false)

        return CharacterViewHolder(itemView, listener)
    }

    override fun getItemCount(): Int {
        return resultist.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(resultist[position])
    }

}