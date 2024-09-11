package com.example.cleanarchitecture.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Developers(val isFavourite : Boolean,
                      val username: String,
                      val avatar: String,
                      val url: String)
