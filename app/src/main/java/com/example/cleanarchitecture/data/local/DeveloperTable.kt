package com.example.cleanarchitecture.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeveloperTable(@PrimaryKey val username: String,
                          val avatar: String,
                          val url: String,
                          val isFavourite: Boolean = false)
