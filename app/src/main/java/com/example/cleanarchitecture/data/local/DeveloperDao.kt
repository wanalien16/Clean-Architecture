package com.example.cleanarchitecture.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.http.GET

@Dao
interface DeveloperDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun InsertDevelopers(developer: DeveloperTable)

    @Query("SELECT * FROM DeveloperTable WHERE isFavourite = 1")
     fun getFavouriteDevelopers(): List<DeveloperTable>

    @Query("SELECT * FROM DeveloperTable")
     fun getAllDevelopers(): List<DeveloperTable>
}