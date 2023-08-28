package com.example.rimoverse


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rimoverse.databinding.CharacterListItemBinding
import com.example.rimoverse.databinding.FragmentCharacterListBinding
import com.example.rimoverse.fragments.CharacterListFragmentDirections
import com.example.rimoverse.models.Character
import com.squareup.picasso.Picasso
class Adapter(private val characterList: MutableList<Character>): RecyclerView.Adapter<Adapter.CharacterListViewHolder>() {

    private lateinit var binding : CharacterListItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        binding = CharacterListItemBinding.inflate(inflater, parent, false)

        return CharacterListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        return holder.bindView(characterList[position])
    }


    inner class CharacterListViewHolder(itemView: CharacterListItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        fun bindView(character: Character) {

            binding.apply {
                tvListItemCharacterName.text = character.name
                listItemCharacterImageView.setOnClickListener {
                    val action =
                        CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment()
                            .setCharacterId(character.id)
                    findNavController(it.findFragment()).navigate(action)
                }
                Picasso.get().load(character.image).into(listItemCharacterImageView)
            }


        }
    }
}