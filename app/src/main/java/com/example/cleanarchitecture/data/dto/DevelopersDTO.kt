package com.example.cleanarchitecture.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DevelopersDTO(
    val name: String?,
    val username: String,
    val avatar: String,
    val url: String,

)
