package com.example.rimoverse.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rimoverse.models.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Repository):
    ViewModel() {
    private val _characterPageLiveData = MutableLiveData<List<CharacterModel>>()
    val characterPageLiveData: LiveData<List<CharacterModel>> = _characterPageLiveData

    init {
        _characterPageLiveData.value = repository.getCharactersPage(1)
    }

    fun refreshCharacter(pageIndex: Int){
        _characterPageLiveData.postValue(repository.getCharactersPage(pageIndex))
    }

}




