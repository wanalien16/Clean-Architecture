package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.data.local.DeveloperTable
import com.example.cleanarchitecture.domain.repository.DeveloperRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ColdFlowUseCase @Inject constructor(private val developerRepo : DeveloperRepo) {
    suspend operator fun invoke(): Flow<List<DeveloperTable>> {
        return developerRepo.getDevelopersFromLocal()
    }

}