package com.example.cleanarchitecture.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.cleanarchitecture.data.local.AppDatabase
import com.example.cleanarchitecture.data.local.DeveloperDao
import com.example.cleanarchitecture.data.remote.ApiService
import com.example.cleanarchitecture.data.repository.DeveloperRepoImpl
import com.example.cleanarchitecture.domain.repository.DeveloperRepo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL =
        "https://private-anon-1db40ca6e1-githubtrendingapi.apiary-mock.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideDeveloperRepository(apiService: ApiService, repoDao: DeveloperDao): DeveloperRepo {
        return DeveloperRepoImpl(apiService,repoDao)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabaseInstance(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java, "App Database").build()
    }

    @Provides
    @Singleton
    fun provideDeveloperDao(database: AppDatabase): DeveloperDao{
        return database.developerDao()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences{

        return context.getSharedPreferences("app_preferences",Context.MODE_PRIVATE)

    }


}


