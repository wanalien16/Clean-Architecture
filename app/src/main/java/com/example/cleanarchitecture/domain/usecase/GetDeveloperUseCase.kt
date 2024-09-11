package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.domain.model.Developers
import com.example.cleanarchitecture.domain.repository.DeveloperRepo
import javax.inject.Inject

class GetDeveloperUseCase @Inject constructor(private val developersRepo: DeveloperRepo){
    suspend operator fun invoke(): List<Developers>{
        return developersRepo.getDevelopersData()
    }
}

