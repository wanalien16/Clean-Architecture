package com.example.cleanarchitecture.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface DeveloperDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertDevelopers(developer: DeveloperTable)

    @Query("SELECT * FROM DeveloperTable WHERE isFavourite = 1")
     fun getFavouriteDevelopers(): List<DeveloperTable>

    @Query("SELECT * FROM DeveloperTable")
     fun getAllDevelopers(): List<DeveloperTable>

     @Query("UPDATE DeveloperTable SET isFavourite = :isFavourite WHERE username = :username")
     fun updateDeveloperFavouriteStatus(username: String, isFavourite: Boolean)
}