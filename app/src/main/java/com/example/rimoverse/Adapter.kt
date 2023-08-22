package com.example.rimoverse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rimoverse.models.Character
import com.squareup.picasso.Picasso

class Adapter(private val character: Character): RecyclerView.Adapter<CharacterViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_list_item,parent,false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        return holder.bindView(character)
    }
}

class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val listItemText : TextView = itemView.findViewById(R.id.characterNameTextView)
    private val listItemImage : ImageView = itemView.findViewById(R.id.characterImageView)
    fun bindView(character: Character){
        listItemText.text = character.name
        Picasso.get().load(character.image).into(listItemImage)

    }
}