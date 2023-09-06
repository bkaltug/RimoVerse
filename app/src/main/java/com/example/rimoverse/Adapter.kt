package com.example.rimoverse


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rimoverse.databinding.CharacterListItemBinding
import com.example.rimoverse.fragments.CharacterListFragmentDirections
import com.example.rimoverse.models.CharacterModel
import com.squareup.picasso.Picasso


class Adapter(private val characterList: List<CharacterModel>): RecyclerView.Adapter<Adapter.CharacterListViewHolder>() {

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
        return holder.bind(characterList[position])
    }


    inner class CharacterListViewHolder(itemView: CharacterListItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        fun bind(characterModel: CharacterModel) {
            binding.apply {
                tvListItemCharacterName.text = characterModel.name
                listItemCharacterImageView.setOnClickListener {
                    val action =
                        CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment()
                            .setCharacterId(characterModel.id!!)
                    findNavController(it.findFragment()).navigate(action)
                }
                Picasso.get().load(characterModel.image).into(listItemCharacterImageView)
            }


        }
    }
}