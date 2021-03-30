package com.example.myapplication.recycler

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.Result

class CharacterViewHolder(
        itemView : View,
        private val listener : Listener
) : RecyclerView.ViewHolder(itemView)
{
    interface Listener{
        fun onCharacterClicked(position: Int)
    }

    fun bind(result : Result?){
        val textView = itemView.findViewById<TextView>(R.id.character_name)
        val imageView = itemView.findViewById<ImageView>(R.id.character_image)
        val test = result?.name

        Log.i("Bind", "Test name = $test")

        textView.text = result?.name
        Glide.with(imageView).load(result?.image).into(imageView)

        itemView.setOnClickListener {
            listener.onCharacterClicked(adapterPosition)
        }
    }
}