package com.example.rimoverse.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.rimoverse.databinding.FragmentCharacterDetailBinding
import com.example.rimoverse.models.CharacterModel
import com.example.rimoverse.network.NetworkLayer
import com.squareup.picasso.Picasso
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

        val serviceGenerator = NetworkLayer.service
        val call = serviceGenerator.getCharacterById(args.characterId)

        call.enqueue(object : Callback<CharacterModel> {
            override fun onResponse(call: Call<CharacterModel>, response: Response<CharacterModel>) {
                if (response.isSuccessful) {
                    binding.apply {
                        Picasso.get().load(response.body()?.image).into(characterDetailImageView)
                        tvCharacterDetailName.text = response.body()?.name.orEmpty()
                        tvCharacterDetailLocation.text = response.body()?.location?.name.orEmpty()
                        tvCharacterDetailOrigin.text = response.body()?.origin?.name.orEmpty()
                        tvCharacterDetailSpecies.text = response.body()?.species.orEmpty()
                        tvCharacterDetailStatus.text = response.body()?.status.orEmpty()
                    }

                //Another option to databinding
                //binding.apply{
                // Picasso.get().load(response.body()?.image).into(characterDetailImageView)
                // }
                // binding.mycharacter = CharacterModel(response.body()?.created.orEmpty(),response.body()?.episode.orEmpty(),response.body()?.gender.orEmpty(),response.body()?.id,response.body()?.image.orEmpty(),response.body()?.location,response.body()?.name.orEmpty(),response.body()?.origin,response.body()?.species.orEmpty(),response.body()?.species.orEmpty(),response.body()?.type.orEmpty(),response.body()?.url.orEmpty(),)
                }
            }

            override fun onFailure(call: Call<CharacterModel>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())

            }
        })
    }
}




