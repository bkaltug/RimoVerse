package com.example.rimoverse.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rimoverse.Adapter
import com.example.rimoverse.R
import com.example.rimoverse.databinding.FragmentCharacterDetailBinding
import com.example.rimoverse.databinding.FragmentCharacterListBinding
import com.example.rimoverse.models.CharacterList
import com.example.rimoverse.network.Service
import com.example.rimoverse.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCharacterListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView = binding.characterListRecyclerView
        val serviceGenerator = ServiceGenerator.buildService(Service::class.java)
        val characterListCall = serviceGenerator.getCharacterList()

        characterListCall.enqueue(object: Callback<CharacterList>{
            override fun onResponse(call: Call<CharacterList>, response: Response<CharacterList>) {
                if (response.isSuccessful){
                    recyclerView.apply {
                        layoutManager = GridLayoutManager(activity,2)
                        adapter = Adapter(response.body()?.results?: arrayListOf())
                    }
                }
            }

            override fun onFailure(call: Call<CharacterList>, t: Throwable) {
                println("Response failed")
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }
        })
        }
    }










