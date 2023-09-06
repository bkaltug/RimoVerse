package com.example.rimoverse.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rimoverse.Adapter
import com.example.rimoverse.viewmodel.MyViewModel
import com.example.rimoverse.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private val viewModel: MyViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentCharacterListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.characterListRecyclerView
        val swipeRefreshLayout = binding.swipeRefreshLayout
        var counter = 1


        recyclerView.apply {
            layoutManager = GridLayoutManager(activity,2)
            adapter = Adapter(viewModel.characterPageLiveData.value.orEmpty())
        }


        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            counter++
            viewModel.refreshCharacter(counter)
        }
        }
    }










