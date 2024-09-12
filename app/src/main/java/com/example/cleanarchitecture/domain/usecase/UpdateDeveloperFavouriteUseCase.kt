package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.domain.repository.DeveloperRepo
import javax.inject.Inject

class UpdateDeveloperFavouriteUseCase @Inject constructor(private val developerRepo: DeveloperRepo) {

    suspend operator fun invoke(username: String, isFavourite: Boolean){
        developerRepo.updateDeveloperFavouriteStatus(username, isFavourite)
    }

}