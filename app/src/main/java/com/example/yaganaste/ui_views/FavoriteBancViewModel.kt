package com.example.yaganaste.ui_views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yaganaste.data.BancsModel
import com.example.yaganaste.data.EntyBancsModel
import com.example.yaganaste.repository.BancsRepository
import com.example.yaganaste.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteBancViewModel @Inject constructor(private val repository: FavoriteRepository): ViewModel() {

    private val _listFavorite = MutableStateFlow(mutableListOf<EntyBancsModel>())
    val vmList: StateFlow<MutableList<EntyBancsModel>> get() = _listFavorite

    fun addFavorite(nBanc: EntyBancsModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBancFav(nBanc)
        }
    }

    fun getLisFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            _listFavorite.value = repository.getBancFav().toMutableList()
        }
    }
}