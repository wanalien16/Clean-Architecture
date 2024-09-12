package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.domain.model.Developers

interface DeveloperRepo {
    suspend fun getDevelopersData():List<Developers>

    suspend fun updateDeveloperFavouriteStatus(username: String, isFavourite: Boolean)
}