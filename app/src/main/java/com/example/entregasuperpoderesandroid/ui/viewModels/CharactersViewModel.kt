package com.example.entregasuperpoderesandroid.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entregasuperpoderesandroid.data.model.SuperHeroCharacter
import com.example.entregasuperpoderesandroid.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _state = MutableStateFlow<List<SuperHeroCharacter>>(emptyList())
    val state: StateFlow<List<SuperHeroCharacter>> get() = _state


    fun getHerosCharacter(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getHeros()
            }

            _state.update { result }
        }
    }


}