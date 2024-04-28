package com.example.yaganaste.ui_views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yaganaste.data.BancsModel
import com.example.yaganaste.domine.ApiResource
import com.example.yaganaste.repository.BancsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BancsViewModel @Inject constructor(private val repository: BancsRepository?) : ViewModel() {

    private val _listGamesVm = MutableStateFlow(mutableListOf<BancsModel>())
    val listGamesVm: StateFlow<MutableList<BancsModel>> get() = _listGamesVm

    private val _shwPb = MutableStateFlow(false)
    val shwPb: StateFlow<Boolean> get() = _shwPb

    private val _msgApiVm = MutableStateFlow("")
    val msgApiVm: StateFlow<String> get() = _msgApiVm

    init {
        getGamesVm()
    }

    private fun getGamesVm() {

        _shwPb.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val response = repository?.getPokemonListRepo()

            when(response) {

                is ApiResource.Success -> {
                    _shwPb.value = false
                    val list = response.data
                    _listGamesVm.value = (list?: mutableListOf()).toMutableList()
                }
                is ApiResource.Error -> {
                    _shwPb.value = false
                    val msg = response.message.toString()
                    _msgApiVm.value = msg
                }
                else -> {}
            }
        }
    }

    /*fun getMessageApiUc(): String {
        Log.d("ERROR_API", msgApi)
        return msgApi
    }*/
}