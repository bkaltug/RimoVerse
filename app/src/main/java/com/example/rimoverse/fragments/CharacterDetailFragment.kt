package com.example.rimoverse.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.rimoverse.R
import com.example.rimoverse.models.Character
import com.example.rimoverse.network.Service
import com.example.rimoverse.network.ServiceGenerator
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailFragment : Fragment() {

    private val args: CharacterDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image = view.findViewById<ImageView>(R.id.characterDetailImageView)
        val name  = view.findViewById<TextView>(R.id.tvCharacterDetailName)
        val location = view.findViewById<TextView>(R.id.tvCharacterDetailLocation)
        val origin = view.findViewById<TextView>(R.id.tvCharacterDetailOrigin)
        val species = view.findViewById<TextView>(R.id.tvCharacterDetailSpecies)
        val status = view.findViewById<TextView>(R.id.tvCharacterDetailStatus)

        val serviceGenerator = ServiceGenerator.buildService(Service::class.java)
        val call = serviceGenerator.getCharacterById(args.characterId)

        call.enqueue(object : Callback<Character> {

            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful) {
                    Picasso.get().load(response.body()?.image).into(image)
                    name.text = response.body()?.name.orEmpty()
                    location.text = response.body()?.location?.name.orEmpty()
                    origin.text = response.body()?.origin?.name.orEmpty()
                    species.text = response.body()?.species.orEmpty()
                    status.text = response.body()?.status.orEmpty()


                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())

            }
        })
    }
}







