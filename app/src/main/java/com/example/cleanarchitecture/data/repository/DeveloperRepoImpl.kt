package com.example.cleanarchitecture.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.cleanarchitecture.data.local.DeveloperDao
import com.example.cleanarchitecture.data.local.DeveloperTable
import com.example.cleanarchitecture.data.remote.ApiService
import com.example.cleanarchitecture.domain.mappers.DevelopersMapper
import com.example.cleanarchitecture.domain.model.Developers
import com.example.cleanarchitecture.domain.repository.DeveloperRepo
import javax.inject.Inject

class DeveloperRepoImpl @Inject constructor(private val apiService: ApiService, private val repoDao: DeveloperDao, private val sharedPreferences: SharedPreferences): DeveloperRepo {

    companion object {
        private const val FIRST_LAUNCH_BY = "first_launch"
    }

    override suspend fun getDevelopersData(): List<Developers> {

        val isFirstLaunch = sharedPreferences.getBoolean(FIRST_LAUNCH_BY, true)

        return if (isFirstLaunch) {
            val response = kotlin.runCatching { apiService.getDevelopers("en") }.getOrElse {
                Log.e("error is", "${it.localizedMessage}")
                emptyList()
            }
            val developerList = response.map { developer ->
                DeveloperTable(
                    username = developer.username,
                    avatar = developer.avatar,
                    url = developer.url,
                    isFavourite = false
                )
            }
            developerList.forEach { developerTable -> repoDao.insertDevelopers(developerTable) }
            return DevelopersMapper.mapDevelopersTableToDevelopers(repoDao.getAllDevelopers())
        } else {
            mergeApiDataWithLocalFavourites()
        }


//        val developerList = response.map { developer -> DeveloperTable(username = developer.username,
//            avatar = developer.avatar,
//            url = developer.url,
//            isFavourite = developer.copy() ) }
//        developerList.forEach { developerTable -> repoDao.insertDevelopers(developerTable)  }

    }


    private suspend fun mergeApiDataWithLocalFavourites(): List<Developers> {

        val localDevelopers = repoDao.getAllDevelopers()
        val apiDevelopers = kotlin.runCatching { apiService.getDevelopers("en") }
            .getOrElse { Log.e("error is", "${it.localizedMessage}") }
        val updatedDevelopers = apiDevelopers.map { apiDev ->
            val localDev = localDevelopers.find { it.username == apiDev.username }
        }

    }


    override suspend fun updateDeveloperFavouriteStatus(username: String, isFavourite: Boolean) {
        repoDao.updateDeveloperFavouriteStatus(username, isFavourite)
    }

}