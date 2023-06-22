package com.example.entregasuperpoderesandroid.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entregasuperpoderesandroid.data.model.Comic
import com.example.entregasuperpoderesandroid.data.model.Serie
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
class CharacterDetailViewModel @Inject constructor(private val repository: Repository ): ViewModel() {

    private val _comics = MutableStateFlow<List<Comic>>(emptyList())
    val comics: StateFlow<List<Comic>> get() = _comics

    private val _series = MutableStateFlow<List<Serie>>(emptyList())
    val series: StateFlow<List<Serie>> get() = _series

    private val _hero = MutableStateFlow<SuperHeroCharacter?>(null)
    val hero: StateFlow<SuperHeroCharacter?> get() = _hero


    fun getComicsByHero(heroID: Int){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getComicsByHero(heroID)
            }
            result.let {
                _comics.update { result }
            }
        }

    }

    fun getSeriesByHero(heroID: Int){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getSeriesByHero(heroID)
            }
            result.let {
                _series.update { result }
            }
        }
    }

    fun getHeroCharacter(heroID: Int){
        viewModelScope.launch {
            repository.getHero(heroID).collect{ hero ->
                _hero.update { hero }
            }
        }
    }
    fun markFavoriteHero(heroID: Int, favorite: Boolean){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.markFavoriteHero(heroID, favorite)
            }
        }
    }
}