package com.example.rimoverse.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rimoverse.Adapter
import com.example.rimoverse.R
import com.example.rimoverse.models.Character
import com.example.rimoverse.network.Service
import com.example.rimoverse.network.ServiceGenerator
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.characterListRecyclerView)

        val serviceGenerator = ServiceGenerator.buildService(Service :: class.java)
        val call = serviceGenerator.getCharacterById(1)

        call.enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful){

                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = Adapter(response.body()!!)
                    }

                }
            }
            override fun onFailure(call: Call<Character>, t: Throwable) {
                t.printStackTrace()
                Log.e("error",t.message.toString())
            }
        })
    }
    }



