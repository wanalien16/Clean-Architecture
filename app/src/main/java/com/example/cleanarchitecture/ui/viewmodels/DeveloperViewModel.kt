package com.example.cleanarchitecture.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.domain.model.Developers
import com.example.cleanarchitecture.domain.repository.DeveloperRepo
import com.example.cleanarchitecture.domain.usecase.GetDeveloperUseCase
import com.example.cleanarchitecture.domain.usecase.UpdateDeveloperFavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeveloperViewModel @Inject constructor(private val getDeveloperUseCase: GetDeveloperUseCase,
    private val updateDeveloperFavouriteUseCase: UpdateDeveloperFavouriteUseCase) :
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
                Log.i("getting", "data $response")
                viewModelScope.launch { _data.value = response }
            }.getOrElse { Log.e("error is", it.localizedMessage)}

        }
    }

     fun toggleFavourite(username: String, isFavourite: Boolean){
         viewModelScope.launch(Dispatchers.IO) { val newFavouriteStatus = !isFavourite
         updateDeveloperFavouriteUseCase.invoke(username,newFavouriteStatus)}



    }
}
