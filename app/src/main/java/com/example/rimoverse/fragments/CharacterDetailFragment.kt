package com.example.rimoverse.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.rimoverse.R
import com.example.rimoverse.models.Character
import com.example.rimoverse.network.Service
import com.example.rimoverse.network.ServiceGenerator
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailFragment() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image = view.findViewById<ImageView>(R.id.characterDetailImageView)
        val name  = view.findViewById<TextView>(R.id.characterDetailNameTextView)
        val location = view.findViewById<TextView>(R.id.characterDetailLocationTextView)
        val origin = view.findViewById<TextView>(R.id.characterDetailOriginTextView)
        val species = view.findViewById<TextView>(R.id.characterDetailSpeciesTextView)
        val status = view.findViewById<TextView>(R.id.characterDetailStatusTextView)

        val serviceGenerator = ServiceGenerator.buildService(Service::class.java)
        val call = serviceGenerator.getCharacterById(16)

        call.enqueue(object : Callback<Character> {

            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful) {
                    Picasso.get().load(response.body()!!.image).into(image)
                    name.text = response.body()!!.name
                    location.text = response.body()!!.location.name
                    origin.text = response.body()!!.origin.name
                    species.text = response.body()!!.species
                    status.text = response.body()!!.status


                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }

        })
    }
}







