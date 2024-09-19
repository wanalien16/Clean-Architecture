package com.example.cleanarchitecture.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.domain.model.Developers
import com.example.cleanarchitecture.domain.usecase.GetDeveloperUseCase
import com.example.cleanarchitecture.domain.usecase.UpdateDeveloperFavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DeveloperViewModel @Inject constructor(
    private val getDeveloperUseCase: GetDeveloperUseCase,
    private val updateDeveloperFavouriteUseCase: UpdateDeveloperFavouriteUseCase
) :
    ViewModel() {

    private val _data = MutableStateFlow<List<Developers>>(emptyList())
    val data: StateFlow<List<Developers>> = _data.asStateFlow()


    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val response = getDeveloperUseCase()
                Timber.i("getting", "data $response")
                viewModelScope.launch { _data.value = response }
            }.getOrElse { Log.e("error is", it.localizedMessage) }


        }
    }

    fun toggleFavourite(username: String, isFavourite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
//            val newFavouriteStatus = !isFavourite


            val developer =
                data.value.find { it.username == username }?.copy(isFavourite = isFavourite)
                    ?: return@launch
            val developerPosition =
                data.value.indexOfFirst { it.username == username }.takeIf { it >= 0 }
                    ?: return@launch
            val newList = data.value.toMutableList()
            newList.removeAt(developerPosition)
            newList.add(developerPosition, developer)
            _data.value = newList
            updateDeveloperFavouriteUseCase(username = username, isFavourite = isFavourite)

        }


    }
}
