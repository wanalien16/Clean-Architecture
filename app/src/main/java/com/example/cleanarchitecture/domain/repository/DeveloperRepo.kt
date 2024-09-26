package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.data.local.DeveloperTable
import com.example.cleanarchitecture.domain.model.Developers
import kotlinx.coroutines.flow.Flow

interface DeveloperRepo {
    suspend fun getDevelopersData():List<Developers>

    suspend fun updateDeveloperFavouriteStatus(username: String, isFavourite: Boolean)

    suspend fun getDevelopersFromLocal(): Flow<List<DeveloperTable>>
}