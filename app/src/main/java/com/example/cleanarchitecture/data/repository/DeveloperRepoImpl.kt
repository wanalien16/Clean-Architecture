package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.local.DeveloperDao
import com.example.cleanarchitecture.data.local.DeveloperTable
import com.example.cleanarchitecture.data.remote.ApiService
import com.example.cleanarchitecture.domain.mappers.DevelopersMapper
import com.example.cleanarchitecture.domain.model.Developers
import com.example.cleanarchitecture.domain.repository.DeveloperRepo
import javax.inject.Inject

class DeveloperRepoImpl @Inject constructor(private val apiService: ApiService, private val repoDao: DeveloperDao): DeveloperRepo {
    override suspend fun getDevelopersData(): List<Developers> {
        val response = apiService.getDevelopers("en")
        val developerList = response.map { developer -> DeveloperTable(username = developer.username,
            avatar = developer.avatar,
            url = developer.url,
            isFavourite = false) }
        developerList.forEach { developerTable -> repoDao.InsertDevelopers(developerTable)  }
        return DevelopersMapper.mapDevelopersTableToDevelopers(repoDao.getAllDevelopers())
    }

    override suspend fun updateDeveloperFavouriteStatus(username: String, isFavourite: Boolean) {
        repoDao.updateDeveloperFavouriteStatus(username, isFavourite)
    }

}