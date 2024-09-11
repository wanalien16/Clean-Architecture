package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.data.remote.ApiService
import com.example.cleanarchitecture.data.repository.DeveloperRepoImpl
import com.example.cleanarchitecture.domain.repository.DeveloperRepo
import com.example.cleanarchitecture.domain.usecase.GetDeveloperUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideGetDeveloperUseCase(developerRepository: DeveloperRepo): GetDeveloperUseCase {
        return GetDeveloperUseCase(developerRepository)
    }

//    @Provides
//    @ViewModelScoped
//    fun provideDeveloperRepository(apiService: ApiService): DeveloperRepo {
//        return DeveloperRepoImpl(apiService)
//
//    }
}