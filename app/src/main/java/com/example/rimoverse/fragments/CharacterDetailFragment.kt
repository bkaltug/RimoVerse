package com.example.rimoverse.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.rimoverse.MainActivity
import com.example.rimoverse.R
import com.example.rimoverse.databinding.FragmentCharacterDetailBinding
import com.example.rimoverse.models.Character
import com.example.rimoverse.network.Service
import com.example.rimoverse.network.ServiceGenerator
import com.squareup.picasso.Picasso
import kotlinx.coroutines.NonDisposableHandle.parent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailFragment : Fragment() {
    private lateinit var binding: FragmentCharacterDetailBinding
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

            binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
            return binding.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val serviceGenerator = ServiceGenerator.buildService(Service::class.java)
        val call = serviceGenerator.getCharacterById(args.characterId)

        call.enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful) {
                    binding.apply {
                        Picasso.get().load(response.body()?.image).into(characterDetailImageView)
                        tvCharacterDetailName.text = response.body()?.name.orEmpty()
                        tvCharacterDetailLocation.text = response.body()?.location?.name.orEmpty()
                        tvCharacterDetailOrigin.text = response.body()?.origin?.name.orEmpty()
                        tvCharacterDetailSpecies.text = response.body()?.species.orEmpty()
                        tvCharacterDetailStatus.text = response.body()?.status.orEmpty()
                    }
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())

            }
        })
    }
}







